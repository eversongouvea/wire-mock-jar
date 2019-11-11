package br.com.wiremock.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.tomakehurst.wiremock.WireMockServer;

@Component
public class Construct {

	@Autowired
	WireMockServer wire;
	
	@PostConstruct
	public void startWireMock() {
		wire.start();
	}
	
	
}
