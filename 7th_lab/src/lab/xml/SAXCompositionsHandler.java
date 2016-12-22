package lab.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import recording.entity.composition.Composition;
import recording.entity.duration.CompositionDuration;
import recording.exception.RecordingException;


import java.util.List;

/**
 * Handler, that parse xml file
 *
 * @author Ihar Zharykau
 */
public class SAXCompositionsHandler extends DefaultHandler {
    private static final Logger log = LogManager.getLogger(SAXCompositionsHandler.class);

    private static final String ELEMENT_NAME = "composition";
    private static final String COMPOSITION_DURATION = "duration";


    /**
     * create object for parse xml file
     *
     * @param resultCompositionList output parameter: parsed compositions
     */
    public SAXCompositionsHandler(List<Composition> resultCompositionList) {
        this.resultCompositionList = resultCompositionList;
        //compositionFactory = CompositionFactory.instance();
    }

    /**
     * parsed compositions
     */
    private List<Composition> resultCompositionList;

    private CompositionsBaseXMLUtils compositionUtils = new CompositionsBaseXMLUtils();

    /**
     * current name of tag
     */
    private String currentName;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentName = qName;
        switch (qName) {
            case ELEMENT_NAME:
                compositionUtils.initDefaultValues();
                break;
            case COMPOSITION_DURATION:
                compositionUtils.initDuration(attributes);
                break;
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (ELEMENT_NAME.equals(qName)) {
            Composition composition = compositionUtils.createComposition();
            resultCompositionList.add(composition);
            log.debug("Added composition : " + composition);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (value.length() == 0) {
            return;
        } else {
            compositionUtils.setSomeValue(currentName, value);
        }
        log.debug("Set value : " + value + " for property : " + currentName);
    }
}
