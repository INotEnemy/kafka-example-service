package com.example.kafkaforpractice.rest.controller;

import com.example.kafkaforpractice.kafka.KafkaService;
import com.example.kafkaforpractice.kafka.trusteddata.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/map")
public class MapsController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping
    public Map postNewMap(@RequestBody Map map) {
        kafkaService.sendCharMap(map);
        return map;
    }

//    @GetMapping("/partition/{partition}/offset/{offset}")
//    public Map getExistsMap(@PathVariable Integer partition, @PathVariable Long offset) {
//        return kafkaService.activeReceiveMap(partition,offset);
//    }
}
