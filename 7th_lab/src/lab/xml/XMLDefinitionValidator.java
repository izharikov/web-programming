package lab.xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 *
 *
 * @author Ihar Zharykau
 */
public class XMLDefinitionValidator {
    public static void main(String... args) {
        System.out.println(new XMLDefinitionValidator().validate("xml/lab.xsd", "xml/compositions.xml"));
    }

    /**
     * validate xml file with xsd file definition
     *
     * @param schemaFileName xsd file name
     * @param xmlFileName    xml file name
     * @return true : if file is valid<br>false : otherwise
     */
    public boolean validate(String schemaFileName, String xmlFileName) {
        File schemaFile = new File(schemaFileName);
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        boolean valid = true;
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Source xmlFile = new StreamSource(new File(xmlFileName));
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
        } catch (IOException | SAXException e) {
            valid = false;
        }
        return valid;
    }

}
