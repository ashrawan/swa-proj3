package com.example.searchserviceelastic.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class ElasticConfig {

    @Value("${app.elastic.host}")
    private String host;

    @Value("${app.elastic.port}")
    private int port;

    @Value("${app.elastic.username}")
    private String userName;

    @Value("${app.elastic.password}")
    private String password;

    @Bean
    public ElasticsearchClient elasticsearchClient() {

//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));

        RestClient httpClient = RestClient.builder(new HttpHost(host, port))
//                .setDefaultHeaders(compatibilityHeaders())
//                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
                .build();

        ElasticsearchTransport transport = new RestClientTransport(httpClient, new JacksonJsonpMapper());
        ElasticsearchClient esClient = new ElasticsearchClient(transport);
        return esClient;
    }

    private Header[] compatibilityHeaders() {
        return new Header[]{new BasicHeader(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7"), new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7")};
    }
}