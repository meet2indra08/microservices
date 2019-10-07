package com.currencyconversion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@FeignClient
public class CurrencyConversionController {
	
	
	  
	  private CurrencyExchangeServiceProxy proxy;
	  
	  //http://localhost:8100/currency-converter/from/EUR/to/INR/quantity/1000
	  
	 @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	  public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
	      @PathVariable BigDecimal quantity) {
		  
		  Map<String, String> uriVariables = new HashMap<>();
		    uriVariables.put("from", from);
		    uriVariables.put("to", to);
		    
		    
		    ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
		            "http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
		            uriVariables);

		        CurrencyConversionBean response = responseEntity.getBody();

		        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
		            quantity.multiply(response.getConversionMultiple()), response.getPort());
		     
		  
	  }
	  
	  @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	  public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
	      @PathVariable BigDecimal quantity) {

	    CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

	  
	    return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
	        quantity.multiply(response.getConversionMultiple()), response.getPort());
	  }


}
