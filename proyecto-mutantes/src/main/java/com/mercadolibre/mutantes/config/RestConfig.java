package com.mercadolibre.mutantes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

//@Configuration
//@ConfigurationProperties("server")
public class RestConfig {

//    private int readTimeout;
//    private int connectTimeout;
//
//    @Bean
//    public RestOperations createRestTemplate(final ClientHttpRequestFactory clientHttpRequestFactory) {
//        return new RestTemplate(clientHttpRequestFactory);
//    }
//
//    @Bean
//    public ClientHttpRequestFactory createClientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeout);
//        httpComponentsClientHttpRequestFactory.setConnectTimeout(connectTimeout);
//        return httpComponentsClientHttpRequestFactory;
//    }
//
//    public int getReadTimeout() {
//        return readTimeout;
//    }
//
//    public void setReadTimeout(int readTimeout) {
//        this.readTimeout = readTimeout;
//    }
//
//    public int getConnectTimeout() {
//        return connectTimeout;
//    }
//
//    public void setConnectTimeout(int connectTimeout) {
//        this.connectTimeout = connectTimeout;
//    }
}