package de.codefor.le.locations.extractor;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/address")
public class AddressExtractorController {

	private static final Logger logger = LoggerFactory.getLogger(AddressExtractorController.class);

	@Autowired(required = false)
	private NER ner;

	@RequestMapping(method = RequestMethod.POST, value = "/extract", consumes="text/plain", produces="application/json")
	@ResponseBody
	public Iterable<String> getLocations(@RequestBody final String content) {
		logger.debug("extract locations from \"{}\"", content);
		if (ner == null) {
			logger.debug("return empty result b/c NER is not initialized!");
		}
		return ner != null ? ner.getLocations(content, true) : Collections.EMPTY_LIST;
	}
}
