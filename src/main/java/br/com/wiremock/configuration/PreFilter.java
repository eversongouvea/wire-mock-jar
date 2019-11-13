package br.com.wiremock.configuration;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

	
	@Autowired
	WireMockServer wire;
	
	
	@Override
	  public String filterType() {
	    return "pre";
	  }
	 
	  @Override
	  public int filterOrder() {
	    return 1;
	  }
	 
	  @Override
	  public boolean shouldFilter() {
	    return wire.isRunning() ? false : true;
	  }
	 
	  @Override
	  public Object run() {
		  
	    RequestContext ctx = RequestContext.getCurrentContext();
	    
	    try {
	    	ctx.setSendZuulResponse(false);
	        ctx.setResponseStatusCode(HttpStatus.SC_TEMPORARY_REDIRECT);
			ctx.getResponse().sendError(500, "Serviço wireMock desligado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return null;
	  }
	  
}
