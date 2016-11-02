import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse("TestDom.xml");
			NodeList bookList = document.getElementsByTagName("book");
			for(int i = 0; i < bookList.getLength(); i++) {
				/*Node book = bookList.item(i);
				NamedNodeMap attrs = book.getAttributes();
				for(int k = 0; k < attrs.getLength(); k++) {
					Node attr =  attrs.item(k);
					System.out.println(attr);
				}*/
				Element book = (Element)bookList.item(i);
				String id = book.getAttribute("id");
				System.out.println(id);
			}
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
