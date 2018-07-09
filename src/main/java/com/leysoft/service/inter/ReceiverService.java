package com.leysoft.service.inter;

import org.springframework.messaging.MessageHeaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leysoft.model.CustomMessage;

public interface ReceiverService {
	
	public void receive(CustomMessage payload, MessageHeaders headers) throws JsonProcessingException;
}
