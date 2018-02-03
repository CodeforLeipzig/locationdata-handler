package de.codefor.le.locations.locator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NominatimAsker {

    private static final Logger logger = LoggerFactory.getLogger(NominatimAsker.class);

    public static final String NOMINATIM_SEARCH_CITY_PREFIX = "Leipzig, ";

    private static final String NOMINATIM_SEARCH_URL = "http://localhost:8083/search?q=%s&format=json";

    private static final int WAIT_BEFORE_EACH_ACCESS_TO_PREVENT_BANNING = 50;

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public Future<List<Nominatim>> execute(final String address) {
        List<Nominatim> result = null;
        try {
            result = getCoords(address);
            Thread.sleep(WAIT_BEFORE_EACH_ACCESS_TO_PREVENT_BANNING);
        } catch (final InterruptedException e) {
            logger.error(e.toString(), e);
        }
        return new AsyncResult<>(result != null ? result : new ArrayList<Nominatim>());
    }

    private List<Nominatim> getCoords(final String address) {
        final String url = String.format(NOMINATIM_SEARCH_URL, address);
        logger.debug("url {}", url);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Nominatim[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Nominatim[].class, "1");
        if(response.getStatusCode().equals(HttpStatus.OK)) {
            final List<Nominatim> result = Arrays.asList(response.getBody());
            logger.debug("nominatim search result: {}", result);
            return result;
        } else {
        	logger.error(response.getStatusCode() + ": " + response.toString());
        }
        return Collections.EMPTY_LIST;
    }
}
