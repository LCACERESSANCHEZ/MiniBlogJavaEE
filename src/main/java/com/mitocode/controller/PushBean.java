package com.mitocode.controller;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class PushBean implements Serializable{

	@Inject
	@Push(channel = "notify")
	private PushContext push;
	
	public void sendMessage() {
		push.send("");		
	}
}
