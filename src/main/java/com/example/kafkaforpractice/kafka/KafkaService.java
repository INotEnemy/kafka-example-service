package com.example.kafkaforpractice.kafka;

import com.example.kafkaforpractice.kafka.trusteddata.Data;
import com.example.kafkaforpractice.navigator.MachuPickchuNavigator;
import com.example.kafkaforpractice.kafka.trusteddata.Map;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class KafkaService {

    private KafkaTemplate<String, Data> template;

    private KafkaTemplate<String, Map> mapKafkaTemplate;

    @Autowired
    public KafkaService(KafkaTemplate<String, Data> template, KafkaTemplate<String, Map> mapKafkaTemplate) {
        this.template = template;
        this.mapKafkaTemplate = mapKafkaTemplate;
    }

    public void mySend(Data data) {
        template.send("test",generateStringKeyBaseOnCurrTime(), data);
    }

    @KafkaListener(topics="test", idIsGroup = false)
    public void myReceive(Data data) {
        int i = data.getAtr2();
        data.setAtr2(i + 1);
        template.send("test1",generateStringKeyBaseOnCurrTime(), data);
//        template.send("maps",key, data);
    }

    public void sendCharMap(Map map) {
        mapKafkaTemplate.send("maps", generateStringKeyBaseOnCurrTime(), map);
    }

    @KafkaListener(topics = "maps")
    public void passiveReceiveMap(Map map) {
        mapKafkaTemplate.send("ways", generateStringKeyBaseOnCurrTime(), findShortestWayOnMap(map));
    }

//    public Map activeReceiveMap(Integer partition, Long offset) {
//        ConsumerRecord<String,Map> receive = mapKafkaTemplate.receive("ways", partition, offset, Duration.ofSeconds(1L));
//        return receive.value();
//    }


    private String generateStringKeyBaseOnCurrTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    private Map findShortestWayOnMap(Map map) {
        MachuPickchuNavigator navigator = new MachuPickchuNavigator();
        return new Map(navigator.searchRoute(map.getMap()));
    }
}
