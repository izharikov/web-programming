package lab.xml;

import recording.entity.composition.Composition;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class StAXCompositionsParser {
    private static final String ELEMENT_NAME = "composition";
    private static final String COMPOSITION_DURATION = "duration";

    public List<Composition> parseCompositions(String xmlFileName) throws XMLStreamException, IOException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader =
                factory.createXMLStreamReader(
                        new FileInputStream(xmlFileName));
        List<Composition> compositions = null;
        CompositionsBaseXMLUtils compositionUtils = new CompositionsBaseXMLUtils();
        String localName = null;
        compositions = new ArrayList<>();
        while (reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    localName = reader.getLocalName();
                    switch (localName) {
                        case ELEMENT_NAME:
                            compositionUtils.initDefaultValues();
                            break;
                        case COMPOSITION_DURATION:
                            compositionUtils.initDuration(reader);
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String value = reader.getText();
                    if (value == null || value.length() == 0) {
                        continue;
                    } else {
                        compositionUtils.setSomeValue(localName, value);
                    };

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if ("composition".equals(reader.getLocalName())) {
                        Composition composition = compositionUtils.createComposition();
                        compositions.add(composition);
                    }
                    break;
            }
        }
        return compositions;
    }

    public static void main(String... args) throws Exception{
        StAXCompositionsParser parser = new StAXCompositionsParser();
        System.out.println(parser.parseCompositions("xml/compositions.xml"));
    }
}
