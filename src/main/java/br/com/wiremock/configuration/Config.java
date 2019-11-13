package br.com.wiremock.configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

@Configuration
public class Config {

	
	@Value("${wiremock.port}")
	private int port;

	@Value("${wiremock.mappings:src/test/resources}")
	private String mappings;

	@Bean
	public WireMockServer createWireMock() {

		WireMockConfiguration configWireMoc = wireMockConfig().port(port).usingFilesUnderDirectory(mappings);
		return new WireMockServer(configWireMoc);

	}
	
	@Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

}
