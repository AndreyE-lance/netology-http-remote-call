package task1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        CloseableHttpClient httpClient = buildClient();
        HttpGet request = new HttpGet("https://cat-fact.herokuapp.com/facts");
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(String.format("Данные получены. Статус ответа сервера: %s", response.getStatusLine()));
                List<Fact> facts = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Fact>>() {
                });
                Comparator<Fact> comparator = Comparator.comparing(Fact::getCreatedAt);
                Collections.sort(facts, comparator);
                facts.forEach(System.out::println);
            } else {
                System.out.println(String.format("Данные не получены. Статус ответа сервера: %s", response.getStatusLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static CloseableHttpClient buildClient() {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
        return httpClient;
    }
}

