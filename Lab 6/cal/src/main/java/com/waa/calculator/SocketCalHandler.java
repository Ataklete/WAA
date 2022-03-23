package com.waa.calculator;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketCalHandler extends TextWebSocketHandler {

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {
		System.out.println("got message"+ message);
		String result = calc(message.getPayload());
		session.sendMessage(new TextMessage(result));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		//send message back to the client
		session.sendMessage(new TextMessage("Connected !"));

	}


	public String calc(String values){
		if(values.contains("+")) {
			String[] calculation = values.split("\\+");
			int value1= Integer.parseInt(calculation[0]);
			int value2= Integer.parseInt(calculation[1]);
			int result = add(value1, value2);
			return values+" = "+result;
		}
		else if(values.contains("-")) {
			String[] calculation = values.split("-");
			int value1= Integer.parseInt(calculation[0]);
			int value2= Integer.parseInt(calculation[1]);
			int result = subtract(value1, value2);
			return values+" = "+result;
		}
		return "input or operator error";
	}
	public int subtract(int x, int y){
		return x-y;
	}
	public int add(int x, int y){
		return x+y;
	}

}