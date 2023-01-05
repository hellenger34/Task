package com.infopolus.task.util;

import com.infopolus.task.domain.Car;
import lombok.SneakyThrows;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Component
public class DataProvider {

    @SneakyThrows
    public List<String> providePeopleNames(final int numberOfNames) {
        try {
            URL url = new URL(String.format("https://namey.muffinlabs.com/name.json?count=%s&with_surname=true&frequency=common", numberOfNames));

            InputStream responseStream = request(url);
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStream, List.class);
        } catch (Exception ex) {
            throw new Exception("Something went wrong with initializing of storage");
        }
    }

    @SneakyThrows
    public List<Car> provideCarModels(final int numberOfCarModels) {
        try {
            URL url = new URL(String.format("https://carapi.app/api/makes?limit=%s", numberOfCarModels));

            InputStream responseStream = request(url);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseStream).path("data");

            return mapper.convertValue(jsonNode, new TypeReference<List<Car>>() {});
        } catch (Exception ex) {
            throw new Exception(String.format("Something went wrong with initializing of storage with error message: %s", ex.getMessage()));
        }
    }

    private static InputStream request(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("accept", "application/json");
        return con.getInputStream();
    }
}
