package br.com.wiremock.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;

import com.github.tomakehurst.wiremock.WireMockServer;

@RestController
@RequestMapping(value = "wiremock")
public class WireMockRedirectController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	WireMockServer wire;

	@GetMapping(path = "/__admin")
	public String admin() {
		
		if(!wire.isRunning())
			return "Server wireMock Off";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.ALL));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		StringBuilder url = new StringBuilder();
		url.append("http://localhost:");
		url.append(wire.port());
		url.append("/__admin");

		return restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class).getBody();
	}
	
	@GetMapping(path = "/**")
	public String api(HttpServletRequest request) {
		
		if(!wire.isRunning())
			return "Server wireMock Off";
		
		String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String subPath = StringUtils.delete(fullPath, "/wiremock");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.ALL));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		StringBuilder url = new StringBuilder();
		url.append("http://localhost:");
		url.append(wire.port());
		url.append(subPath);

		return restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class).getBody();
	}

}
