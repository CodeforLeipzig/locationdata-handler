package de.codefor.le.locations.locator;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NominatimAskerTest {

    private final NominatimAsker asker = new NominatimAsker();

    @Test
    public void executeWithEmptyAddress() {
        assertNotNull(asker.execute(null));
        assertNotNull(asker.execute(""));
        assertNotNull(asker.execute(" "));
    }

    @Test
    public void executeWithValidDistrictInLeipzig() throws InterruptedException, ExecutionException {
        final Future<List<Nominatim>> future = asker.execute(NominatimAsker.NOMINATIM_SEARCH_CITY_PREFIX + "Grünau");
        checkResults(future);
    }

    @Test
    public void executeWithValidAddressInLeipzig() throws InterruptedException, ExecutionException {
        final Future<List<Nominatim>> future = asker.execute(NominatimAsker.NOMINATIM_SEARCH_CITY_PREFIX
                + "Weißenfelser");
        checkResults(future);
    }

    void checkResults(final Future<List<Nominatim>> future) throws InterruptedException, ExecutionException {
        assertNotNull(future);
        final List<Nominatim> res = future.get();
        assertNotNull(res);
        final Nominatim nominatim = res.get(0);
        assertNotNull(nominatim.getLat());
        assertNotNull(nominatim.getLon());
    }
}
