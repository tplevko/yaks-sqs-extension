package dev.yaks.testing.sqs;

public class AwsSqsSettings {

    private static final String AWS_SQS_PROPERTY_PREFIX = "aws-sqs.";
    private static final String AWS_SQS_QUEUE_NAME = AWS_SQS_PROPERTY_PREFIX + "queueName";
    private static final String AWS_SQS_ACCESS_KEY = AWS_SQS_PROPERTY_PREFIX + "accessKey";
    private static final String AWS_SQS_SECRET_KEY  = AWS_SQS_PROPERTY_PREFIX + "secretKey";
    private static final String AWS_SQS_REGION  = AWS_SQS_PROPERTY_PREFIX + "region";
    private static final String AWS_SQS_ACCOUNT_ID = AWS_SQS_PROPERTY_PREFIX + "accountId";
}
