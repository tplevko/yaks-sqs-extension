//package dev.yaks.testing.sqs.endpoint;
//
//import org.springframework.beans.factory.DisposableBean;
//
//import com.consol.citrus.context.TestContext;
//import com.consol.citrus.endpoint.AbstractEndpoint;
//import com.consol.citrus.endpoint.Endpoint;
//import com.consol.citrus.endpoint.EndpointConfiguration;
//import com.consol.citrus.messaging.Consumer;
//import com.consol.citrus.messaging.Producer;
//
//import java.util.Map;
//
//public class AwsSqsEndpoint extends AbstractEndpoint implements DisposableBean {
//
//    /**
//     * Default constructor using endpoint configuration.
//     *
//     * @param endpointConfiguration
//     */
//    public AwsSqsEndpoint(EndpointConfiguration endpointConfiguration) {
//        super(endpointConfiguration);
//    }
//
//    @Override
//    public Producer createProducer() {
//        return null;
//    }
//
//    @Override
//    public Consumer createConsumer() {
//        return null;
//    }
//
//    public AwsSqsEndpointConfiguration getEndpointConfiguration() {
//        return (KafkaEndpointConfiguration)super.getEndpointConfiguration();
//    }
//
//    protected Endpoint createEndpoint(String resourcePath, Map<String, String> parameters, TestContext context) {
//        JmxClient client = new JmxClient();
//
//        if (resourcePath.contains("platform")) {
//            client.getEndpointConfiguration().setServerUrl("platform");
//        } else {
//            client.getEndpointConfiguration().setServerUrl("service:jmx:" + resourcePath);
//        }
//
//        enrichEndpointConfiguration(client.getEndpointConfiguration(),
//            getEndpointConfigurationParameters(parameters, JmxEndpointConfiguration.class), context);
//        return client;
//    }
//
//
//    @Override
//    public void destroy() throws Exception {
//
//    }
//}
