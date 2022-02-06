package com.agent.agent.config;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
@Configuration
public class RestTemplateConfig {
//
//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate (RestTemplateBuilder builder) {
//        return builder
//                .build();
//    }
//    private HttpComponentsClientHttpRequestFactory disabless(){
//        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
//            @Override
//            public boolean isTrusted(X509Certificate[] X509Certificates, String s) throws CertificateException {
//                return true;
//            }
//        };
//        SSLContext sslcontext = null;
//        try {
//            sslcontext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null,acceptingTrustStrategy).build();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslcontext, new NoopHostnameVerifier());
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory (csf).build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClient);
//        return requestFactory;
//    }
//
//}

        @Bean
        public RestTemplate restTemplate(RestTemplateBuilder builder) throws NoSuchAlgorithmException, KeyManagementException {

            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();
            HttpComponentsClientHttpRequestFactory customRequestFactory = new HttpComponentsClientHttpRequestFactory();
            customRequestFactory.setHttpClient(httpClient);
            return builder.requestFactory(() -> customRequestFactory).build();
        }
    }