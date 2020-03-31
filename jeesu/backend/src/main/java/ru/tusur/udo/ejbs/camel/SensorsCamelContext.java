package ru.tusur.udo.ejbs.camel;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import javax.ejb.Stateless;

@Stateless
public class SensorsCamelContext extends DefaultCamelContext {

    private ProducerTemplate apiControllerTemplate;
    private ConsumerTemplate wsConsumerTemplate;


    public SensorsCamelContext() {
        apiControllerTemplate = createProducerTemplate();
        wsConsumerTemplate = createConsumerTemplate();
    }

    public ConsumerTemplate getWsConsumerTemplate() {
        return wsConsumerTemplate;
    }

    public ProducerTemplate getApiControllerTemplate() {
        return apiControllerTemplate;
    }
}
