package dev.yaks.testing.sqs.client;

import com.consol.citrus.context.TestContext;
import com.consol.citrus.endpoint.AbstractEndpoint;
import com.consol.citrus.endpoint.EndpointConfiguration;
import com.consol.citrus.message.Message;
import com.consol.citrus.messaging.Consumer;
import com.consol.citrus.messaging.Producer;
import com.consol.citrus.messaging.ReplyConsumer;

import javax.management.Notification;
import javax.management.NotificationListener;

public class AwsSqsClient  extends AbstractEndpoint implements Producer, ReplyConsumer, NotificationListener {
    /**
     * Default constructor using endpoint configuration.
     *
     * @param endpointConfiguration
     */
    public AwsSqsClient(EndpointConfiguration endpointConfiguration) {
        super(endpointConfiguration);
    }

    @Override
    public Producer createProducer() {
        return null;
    }

    @Override
    public Consumer createConsumer() {
        return null;
    }

    @Override
    public void send(Message message, TestContext context) {

    }

    @Override
    public Message receive(String selector, TestContext context) {
        return null;
    }

    @Override
    public Message receive(String selector, TestContext context, long timeout) {
        return null;
    }

    @Override
    public Message receive(TestContext context) {
        return null;
    }

    @Override
    public Message receive(TestContext context, long timeout) {
        return null;
    }

    @Override
    public void handleNotification(Notification notification, Object o) {

    }
}
