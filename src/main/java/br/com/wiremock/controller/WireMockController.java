package br.com.wiremock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tomakehurst.wiremock.WireMockServer;

@RestController
@RequestMapping(value = "wiremock-services")
public class WireMockController {

	@Autowired
	WireMockServer wire;
	
	@GetMapping(path = "/start")
	public String start() {
		
		wire.start();
		wire.resetToDefaultMappings();
		return  String.format("Iniciando wireMock na porta: %d - Url alternativa: http://hots/wiremock", wire.port()) ;
		
	}
	
	@GetMapping(path = "/stop")
	public String stop() {
		
		wire.stop();
		return "Parando WireMock";
		
	}
	
}
