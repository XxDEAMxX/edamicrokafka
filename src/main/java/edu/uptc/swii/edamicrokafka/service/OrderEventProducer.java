package edu.uptc.swii.edamicrokafka.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import edu.uptc.swii.edamicrokafka.model.CustomerOrder;
import edu.uptc.swii.edamicrokafka.utils.JsonUtils;


@Service
public class OrderEventProducer {
    private static final String TOPIC_ADD = "addorder_events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateAdd;
    public void sendAddOrderEvent(CustomerOrder custorder){
        String json = null;
        JsonUtils jsonUtils = new JsonUtils();
        json=jsonUtils.toJson(custorder);   
        kafkaTemplateAdd.send(TOPIC_ADD, json);
    }
}
