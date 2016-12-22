package lab.xml;

import recording.entity.composition.Composition;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class CompositionsXmlConverter {
    private static final String XML_FILE_NAME = "xml/compositions.xml";
    private static final String XSD_FILE_NAME = "xml/lab.xsd";

    public static void main(String... args) throws Exception {
        XMLDefinitionValidator validator = new XMLDefinitionValidator();
        if ( validator.validate(XSD_FILE_NAME, XML_FILE_NAME)) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            InputStream is = new FileInputStream(XML_FILE_NAME);
            List<Composition> compositions = new ArrayList<>();
            SAXCompositionsHandler handler = new SAXCompositionsHandler(compositions);
            parser.parse(is, handler);
            System.out.println(compositions);
        }
    }
}
