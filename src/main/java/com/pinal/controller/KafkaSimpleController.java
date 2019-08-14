package com.pinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinal.entity.SimpleModel;

@RestController
@RequestMapping("/api/kafka")
public class KafkaSimpleController {

	private KafkaTemplate<String, SimpleModel> kafkaTemplate;
	
	@Autowired
	public KafkaSimpleController(KafkaTemplate<String, SimpleModel> kafkaTemplate) {
		this.kafkaTemplate=kafkaTemplate;
	}
	
	@PostMapping
	public void post(@RequestBody SimpleModel model) {
		kafkaTemplate.send("KafkaTraining",model);
	}
	
	@KafkaListener(topics = "KafkaTraining")
	public void getFromKafka(SimpleModel model) {
		System.out.println(model.toString());
	}
}
