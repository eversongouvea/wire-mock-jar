package br.com.wiremock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tomakehurst.wiremock.WireMockServer;

@RestController
@RequestMapping(value = "wiremock-services")
public class WireMockController {

	@Autowired
	WireMockServer wire;
	
	@Value("${zuul.routes.wiremock.url}")
	String path;
	
	@GetMapping(path = "/start")
	public String start() {
		
		wire.start();
		wire.resetToDefaultMappings();
		return  String.format("Iniciando wireMock na porta: %d - Url alternativa: %s",wire.port(),path);
		
	}
	
	@GetMapping(path = "/stop")
	public String stop() {
		
		wire.stop();
		return "Parando WireMock";
		
	}
	
}
