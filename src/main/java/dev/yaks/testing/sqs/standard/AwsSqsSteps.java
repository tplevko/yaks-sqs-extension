package dev.yaks.testing.sqs.standard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.context.TestContext;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.PurgeQueueInProgressException;
import software.amazon.awssdk.services.sqs.model.QueueAttributeName;
import software.amazon.awssdk.services.sqs.model.QueueDoesNotExistException;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class AwsSqsSteps {

    @CitrusResource
    private TestContext context;

    private static final String AWS_SQS_PROPERTY_PREFIX = "aws-sqs.";
    private static final String AWS_SQS_QUEUE_NAME = AWS_SQS_PROPERTY_PREFIX + "queueName";
    private static final String AWS_SQS_ACCESS_KEY = AWS_SQS_PROPERTY_PREFIX + "accessKey";
    private static final String AWS_SQS_SECRET_KEY = AWS_SQS_PROPERTY_PREFIX + "secretKey";
    private static final String AWS_SQS_REGION = AWS_SQS_PROPERTY_PREFIX + "region";
    private static final String AWS_SQS_ACCOUNT_ID = AWS_SQS_PROPERTY_PREFIX + "accountId";

    private SqsClient sqsClient;
    private String queueUrlPrefix;
    private String queueArnPrefix;

    @Before
    public void before(Scenario scenario) {
    }

    @Given("initialize SQS client")
    public void initializeSqsClient() {

        sqsClient = SqsClient.builder().region(Region.of((String) context.getVariables().get(AWS_SQS_REGION)))
            .credentialsProvider(() -> AwsBasicCredentials
                .create((String) context.getVariables().get(AWS_SQS_ACCESS_KEY), (String) context.getVariables().get(AWS_SQS_SECRET_KEY))).build();

        queueUrlPrefix = String
            .format("https://sqs.%s.amazonaws.com/%s/", context.getVariables().get(AWS_SQS_REGION), context.getVariables().get(AWS_SQS_ACCOUNT_ID));
        queueArnPrefix =
            String.format("arn:aws:sqs:%s:%s:", context.getVariables().get(AWS_SQS_REGION), context.getVariables().get(AWS_SQS_ACCOUNT_ID));
    }

    @Given("send message: {string} to SQS queue: {string}")
    public void sendSqsMessage(String message, String queueName) {

        sqsClient.sendMessage(SendMessageRequest.builder()
            .queueUrl(getQueueUrl(queueName))
            .messageBody(message)
            .delaySeconds(10)
            .build());
    }

    @Then("verify that the message from {string} queue {string} has content {string}")
    public void verifyMessageContent(String queue, String content) {
        assertThat(getMessages(queue).get(0).body(), equalTo(content));
    }

    private String getQueueUrl(String queue) {
        return queueUrlPrefix + queue;
    }

    public List<Message> getMessages(String queueName) {
        final int queueSize = getQueueSize(queueName);
        List<Message> allMessages = new ArrayList<>();

        while (queueSize != allMessages.size()) {
            allMessages.addAll(sqsClient.receiveMessage(
                b -> b.queueUrl(getQueueUrl(queueName))
                    .maxNumberOfMessages(10).attributeNames(QueueAttributeName.ALL)
                    .build()).messages()
            );
        }

        return allMessages;
    }

    /**
     * Gets the number of messages currently in the queue.
     *
     * @return queue size
     */
    public int getQueueSize(String queueName) {
        return Integer.parseInt(
            sqsClient.getQueueAttributes(b -> b.queueUrl(getQueueUrl(queueName))
                .attributeNames(QueueAttributeName.APPROXIMATE_NUMBER_OF_MESSAGES)
                .build()).attributes().get(QueueAttributeName.APPROXIMATE_NUMBER_OF_MESSAGES)
        );
    }

    /**
     * Removes all messages from the queue.
     *
     * @param queueNames queue names
     */
    public void purge(String... queueNames) {
        for (String queueName : queueNames) {
            try {
                sqsClient.purgeQueue(b -> b.queueUrl(getQueueUrl(queueName)).build());
            } catch (QueueDoesNotExistException e) {
                // ignore
            } catch (PurgeQueueInProgressException ex) {
                // If for some reason some other purge queue is in progress, wait and retry
                //                log.debug("Purging " + queueName + " threw PurgeQueueInProgressException, waiting and retrying");
                //                TestUtils.sleepIgnoreInterrupt(90000L);
                sqsClient.purgeQueue(b -> b.queueUrl(getQueueUrl(queueName)).build());
            }
        }
    }
}
