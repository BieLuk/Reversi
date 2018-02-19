package sample.App;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sample.Exception.WrongArgumentException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

class XMLReader {

    String getWord(String lang, String word) throws ParserConfigurationException, IOException, SAXException, WrongArgumentException {
        String returnWord = null;
        File fXmlFile = new File("Message.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("language");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if(element.getAttribute("name").equals(lang))
                    returnWord = element.getElementsByTagName(word).item(0).getTextContent();
            }
        }
        return returnWord;
    }
}
