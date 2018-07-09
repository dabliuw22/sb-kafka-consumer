package com.leysoft.service.imple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leysoft.model.CustomMessage;
import com.leysoft.service.inter.ReceiverService;

@Service
public class ReceiverServiceImp implements ReceiverService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverServiceImp.class);
	
	@Autowired
	private ObjectMapper mapper;
	
	@KafkaListener(topics = {"${kafka.topic}"})
	@Override
	public void receive(@Payload CustomMessage payload, 
			@Headers MessageHeaders headers) throws JsonProcessingException {
		headers.keySet().forEach(key -> {
			Object value = headers.get(key);
			LOGGER.info("Header <{}: {}>", key, value);
		});
		String info = mapper.writeValueAsString(payload);
		LOGGER.info("receive: {}", info);
	}
}
