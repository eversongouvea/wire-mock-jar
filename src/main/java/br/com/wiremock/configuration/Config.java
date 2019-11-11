package br.com.wiremock.configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

@Configuration
public class Config {

	@Bean
	public WireMockServer createWireMock() {

		WireMockConfiguration configWireMoc = wireMockConfig().port(8090);
		WireMockServer wireMockServer = new WireMockServer(configWireMoc);
		return wireMockServer;
		
	}
	
}
