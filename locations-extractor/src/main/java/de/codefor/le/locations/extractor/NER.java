package de.codefor.le.locations.extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations.AnswerAnnotation;
import edu.stanford.nlp.ling.CoreLabel;

@Component
public class NER {

    private static final Logger logger = LoggerFactory.getLogger(NER.class);

    private static final String BLACKLIST_FILE = "classpath:locationBlacklist";

    private static final String BLACKLIST_COMMENT = "#";

    private static final String SERIALIZED_CLASSIFIER = "dewac_175m_600.crf.ser.gz";

    @Autowired
    private ResourceLoader resourceLoader;

    private AbstractSequenceClassifier<CoreLabel> classifier;

    private Collection<String> blackListedLocations;

    public NER() {
        try {
            classifier = CRFClassifier.getClassifier(new File(SERIALIZED_CLASSIFIER));
        } catch (ClassCastException | ClassNotFoundException | IOException e) {
            Throwables.throwIfUnchecked(e);
        }
    }

    public Collection<String> getLocations(final String text, final boolean removeBlackListed) {
        final Set<String> result = new HashSet<>();
        for (final List<CoreLabel> classifiedSentences : classifier.classify(text)) {
            for (final CoreLabel coreLabel : classifiedSentences) {
                if (coreLabel.get(AnswerAnnotation.class).equals("I-LOC")) {
                    final String originalText = coreLabel.originalText();
                    logger.trace("{}", originalText);
                    result.add(originalText);
                }
            }
        }
        if (removeBlackListed) {
            if (blackListedLocations == null) {
                blackListedLocations = initBlackListedLocations();
            }
            blackListCheck(result);
        }
        logger.debug("{} locations found: {}", result.size(), result);
        return result;
    }

    private void blackListCheck(final Iterable<String> results) {
        final Iterator<String> iterator = results.iterator();
        while (iterator.hasNext()) {
            final String next = iterator.next();
            if (blackListedLocations.contains(next)) {
                logger.debug("remove blacklisted location {}", next);
                iterator.remove();
            }
        }
    }

    Collection<String> initBlackListedLocations() {
        final Collection<String> blacklist = new HashSet<>();
        try {
            new BufferedReader(new InputStreamReader(resourceLoader.getResource(BLACKLIST_FILE).getInputStream())).lines().forEach(line -> {
                if (!Strings.isNullOrEmpty(line) && !line.startsWith(BLACKLIST_COMMENT)) {
                    blacklist.add(line);
                }
            });
        } catch (final IOException e) {
            Throwables.throwIfUnchecked(e);
        }
        logger.debug("initialized location blacklist: {}", blacklist);
        return blacklist;
    }
}
