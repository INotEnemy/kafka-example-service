package com.example.kafkaforpractice.kafka.trusteddata;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data implements Serializable {
    public String atr1;
    public Integer atr2;
}
