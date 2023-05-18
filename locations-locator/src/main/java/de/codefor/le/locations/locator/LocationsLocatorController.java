package de.codefor.le.locations.locator;

import java.util.List;
import java.util.concurrent.Future;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping(value = "/api/locations")
public class LocationsLocatorController {

	private static final Logger logger = LoggerFactory.getLogger(LocationsLocatorController.class);

	@Autowired
	NominatimAsker nominatimAsker;

	@RequestMapping(value = "/locate", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Point getLocation(@RequestParam("location") String location) {
		Point point = null;
		try {
			final Future<List<Nominatim>> nomFutures = nominatimAsker
					.execute(NominatimAsker.NOMINATIM_SEARCH_CITY_PREFIX + location);
			final List<Nominatim> nominatim = nomFutures.get();
			logger.debug("{} coords: {}", location, nominatim);
			if (!nominatim.isEmpty()) {
				for (final Nominatim n : nominatim) {
					point = getCoords(n);
					if (point != null) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return point;
	}

	private Point getCoords(Nominatim nominatim) {
		final String lat = nominatim.getLat();
		final String lon = nominatim.getLon();
		if (NumberUtils.isCreatable(lat) && NumberUtils.isCreatable(lon)) {
			final Point point = new Point(Double.valueOf(lat), Double.valueOf(lon));
			logger.debug("found point {}", point);
			return point;
		}
		logger.warn("latitude {} and longitude {} must be non-empty numeric", lat, lon);
		return null;
	}
}
