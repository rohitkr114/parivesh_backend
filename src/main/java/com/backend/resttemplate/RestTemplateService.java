package com.backend.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class RestTemplateService {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * <p>
     * <b>createRequestFactory</b></br>
     * Method to create HttpComponentsClientHttpRequestFactory with ignore SSL cert.
     * </p>
     *
     * @return
     */
    private static HttpComponentsClientHttpRequestFactory createRequestFactory() {
        try {
            SSLContextBuilder sslContext = new SSLContextBuilder();
            sslContext.loadTrustMaterial(null, new TrustAllStrategy());
            CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext.build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(client);
            requestFactory.setConnectTimeout(5000);
            requestFactory.setReadTimeout(5000);
            return requestFactory;
        } catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException var3) {
            throw new IllegalStateException("Couldn't create HTTP Request factory ignore SSL cert validity: ", var3);
        }
    }

    @Autowired
    public RestTemplateService() {
        restTemplate.setRequestFactory(createRequestFactory());
        log.info("Created RestTemplate");
    }

    /**
     * <p>
     * <b>sendRequest</b></br>
     * wrapper method for resttemplate exchange request.
     * </p>
     *
     * @param <T>
     * @param url
     * @param method
     * @param requestEntity
     * @param responseType
     * @param uriVariables
     * @return
     */
    public <T> ResponseEntity<T> sendRequest(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                             Class<T> responseType, Object uriVariables) {
        return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
    }
}
