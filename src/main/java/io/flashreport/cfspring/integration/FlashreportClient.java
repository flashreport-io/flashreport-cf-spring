package io.flashreport.cfspring.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;

/**
 * Simple client to send report generation requests to flashreport.io, as well as getting information
 * about a report request.
 * <p>
 * The flashreport api key is extracted from the VCAP_SERVICES environment variable, which is set by Cloud Foundry.
 * <p>
 * Created by Nicolas Lejeune on 18/06/15.
 */

public class FlashreportClient {

    private static final String CREATE_URL = "https://gateway.flashreport.io/api/v1/report/new";

    private String API_KEY;


    /**
     * Request the generation of a report with the specified content.
     *
     * @param content json or xml data
     * @return the URI representing the report
     */
    public URI generateReport(String content) {
        return generateReport(content, null, null);
    }

    /**
     * Request the generation of a report with the specified content and title.
     *
     * @param content json or xml data
     * @param title   a title for your report
     * @return the URI representing the report
     */
    public URI generateReport(String content, String title) {
        return generateReport(content, title, null);
    }

    /**
     * Request the generation of a report with the specified content, title and template.
     * The template must have been previously registered in the dashboard.
     *
     * @param content json or xml data
     * @param title   a title for your report
     * @return the URI representing the report
     */
    public URI generateReport(String content, String title, String template) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entityWithBody = new HttpEntity<>(content, getHeaders());

        String url = CREATE_URL;
        if (title != null) url += "?title=" + title;
        if (title != null && template != null) url += "&template=" + template;
        if (title == null && template != null) url += "?template=" + template;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entityWithBody, String.class);

        if (response.getStatusCode().equals(HttpStatus.CREATED)) {
            return response.getHeaders().getLocation();
        } else {
            throw new RuntimeException("Received unexpected response code : " + response.getStatusCode());
        }
    }

    private String extractApiKey() throws IllegalStateException {

        if (API_KEY != null) return API_KEY;

        String vcap_services = System.getenv("VCAP_SERVICES");
        if (vcap_services == null) {
            throw new IllegalStateException("Unable to get VCAP_SERVICES from environment.");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readValue(vcap_services, JsonNode.class);
            API_KEY = jsonNode.get("flashreport").get(0).get("credentials").get("apiKey").asText();
        } catch (IOException | NullPointerException e) {
            throw new IllegalStateException(
                    "Unable to extract api key from VCAP_SERVICES. Did you bind a flashreport service to your app?");
        }

        return API_KEY;
    }


    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String basicHeader = "Basic " + Base64.getEncoder().encodeToString((extractApiKey() + ":").getBytes());
        httpHeaders.add("Authorization", basicHeader);
        return httpHeaders;
    }


}
