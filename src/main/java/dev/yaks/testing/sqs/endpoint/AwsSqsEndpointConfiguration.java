package dev.yaks.testing.sqs.endpoint;

import com.consol.citrus.endpoint.AbstractPollableEndpointConfiguration;

public class AwsSqsEndpointConfiguration extends AbstractPollableEndpointConfiguration {

    private String queueName;
    private String accessKey;
    private String secretKey;
    private String region;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
