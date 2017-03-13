package lab.xml;

import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import recording.entity.composition.Composition;
import recording.entity.duration.CompositionDuration;
import recording.exception.RecordingException;
import recording.factory.CompositionFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class DOMCompositionsParser {
    public List<Composition> parse(String fileName) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(fileName));
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("composition");
        List<Composition> compositions = new ArrayList<>();
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                int year = Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent());
                int daysInTop = Integer.parseInt(eElement.getElementsByTagName("days-in-top").item(0).getTextContent());
                Element durElement = (Element) eElement.getElementsByTagName("duration").item(0);
                CompositionDuration cd = getDurationFromElement(durElement);
                compositions.add(CompositionFactory.instance().getComposition(type, name, cd, year, daysInTop));
            }
        }
        return compositions;
    }

    private CompositionDuration getDurationFromElement(Element durElement) throws RecordingException{
        long s = 0;
        long h = 0;
        long m = 0;
        String sStr = durElement.getAttribute("sec");
        String mStr = durElement.getAttribute("min");
        String hStr = durElement.getAttribute("hours");
        if ( sStr != null && sStr.length() > 0) {
            s = Long.parseLong(sStr);
        }
        if ( mStr != null && mStr.length() > 0) {
            m = Long.parseLong(mStr);
        }
        if ( hStr != null && hStr.length() > 0) {
            h = Long.parseLong(hStr);
        }
        return new CompositionDuration(h, m, s);
    }

    public static void main(String... args) throws Exception {
        new DOMCompositionsParser().parse("xml/compositions.xml");
    }
}
