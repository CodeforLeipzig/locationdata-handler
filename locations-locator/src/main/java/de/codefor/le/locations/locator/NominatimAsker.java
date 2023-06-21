package de.codefor.le.locations.locator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class NominatimAsker {

    private static final Logger logger = LoggerFactory.getLogger(NominatimAsker.class);

    public static final String NOMINATIM_SEARCH_HOST = "NOMINATIM_SEARCH_HOST";
    public static final String NOMINATIM_SEARCH_CITY_PREFIX = "Leipzig, ";

    private static final String NOMINATIM_SEARCH_URL = "http://%s/search?q=%s&format=json";

    private static final int WAIT_BEFORE_EACH_ACCESS_TO_PREVENT_BANNING = 50;

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public Future<List<Nominatim>> execute(final String address) {
        return CompletableFuture.supplyAsync(() -> {
            List<Nominatim> result = getCoords(address);
            try {
                Thread.sleep(WAIT_BEFORE_EACH_ACCESS_TO_PREVENT_BANNING);
            } catch (final InterruptedException e) {
                logger.error(e.toString(), e);
            }
            return result != null ? result : new ArrayList<>();
        });
    }

    private List<Nominatim> getCoords(final String address) {
        String host = System.getenv(NOMINATIM_SEARCH_HOST);
        if (host == null || host.isBlank()) {
            host = "localhost:8083";
        }
        final String url = String.format(NOMINATIM_SEARCH_URL, host, address);
        logger.debug("url {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        //headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Nominatim[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Nominatim[].class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                final List<Nominatim> result = Arrays.asList(Objects.requireNonNull(response.getBody()));
                logger.debug("nominatim search result: {}", result);
                return result;
            } else {
                logger.error(response.getStatusCode() + ": " + response);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
