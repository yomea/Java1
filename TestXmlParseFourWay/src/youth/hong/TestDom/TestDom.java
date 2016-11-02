package youth.hong.TestDom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestDom {
	
	private static List<Book> bookSuffle = new ArrayList<Book>();
	
	/**
	 * ��ȡDocumentBuilder����
	 */
	
	public DocumentBuilder getDocumentBuilder() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documentBuilder;
	}
	
	/**
	 * dom��������Xml�ļ�
	 */
	public void domParse() {
		try {
			Document document = getDocumentBuilder().parse("src/TestJDom/TestDom.xml");
			NodeList bookList = document.getElementsByTagName("book");
			for(int i = 0; i < bookList.getLength(); i++) {
				String id = null;
				String name = null;
				String author = null;
				String year = null;
				//�ڲ�֪���������Ƶ������ʹ��
				/*Node book = bookList.item(i);
				NamedNodeMap attrs = book.getAttributes();
				for(int j = 0; j < attrs.getLength(); j++) {
					Node attr = attrs.item(j);
					System.out.println(attr.getNodeName() + "  " + attr.getNodeValue());
				}*/
				
				//��֪���������Ƶ������ʹ��
				/*Element book = (Element)bookList.item(i);
				String id = book.getAttribute("id");
				String className = book.getAttribute("class"); 
				System.out.println(id + "  " + className);
				*/
				Node book = bookList.item(i);
				Element bookElement = (Element)book;
				id = bookElement.getAttribute("id");
				NodeList childNodes = book.getChildNodes();
				System.out.println(childNodes.getLength());
				for(int k = 0; k < childNodes.getLength(); k++) {
					Node childNode = childNodes.item(k);
					if(childNode.getNodeType() == Node.ELEMENT_NODE) {
						String value = childNode.getTextContent();
						String nodeName = childNode.getNodeName();
						switch(nodeName) {
						case "name" : name = value; value = null; break;
						case "author" : author = value; value = null; break;
						case "year" :  year = value; value = null; break;
						}
						//System.out.println(childNode.getNodeName() + "  " + childNode.getFirstChild().getNodeValue());
						
					}
					
				}
				bookSuffle.add(new Book(id,name,author,year));
				//System.out.println();
			}
			for(int i = 0; i < bookSuffle.size(); i++) {
				System.out.println(bookSuffle.get(i));
			}
		}  catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ����Xml�ļ���Ҫ�����౾�����ͨ��forѭ�������ɣ�������Ӧ������һ��book����ȥ�洢����
	 * @param args
	 */
	
	public void creatXml() {
		DocumentBuilder documentBuilder = this.getDocumentBuilder();
		//����document
		Document document = documentBuilder.newDocument();
		//ȥ��standalone����
		document.setXmlStandalone(true);
		//����bookStore�ڵ�
		Element bookStore = document.createElement("bookStore");
		//����book�ڵ�
		Element book1 = document.createElement("book");
		Element book2 = document.createElement("book");
		Element name1 = document.createElement("name");
		Element author1 = document.createElement("author");
		Element year1 = document.createElement("year");
		Element price1 = document.createElement("price");
		Element name2 = document.createElement("name");
		Element author2 = document.createElement("author");
		Element year2 = document.createElement("year");
		Element price2 = document.createElement("price");
		/*//�����ı��ڵ�
		Node nameText = document.createTextNode("������");
		//����ı��ڵ�
		name.appendChild(nameText);*/
		name1.setTextContent("Ȯҹ��");
		year1.setTextContent("2008");
		author1.setTextContent("����������");
		price1.setTextContent("200$");
		book1.appendChild(name1);
		book1.appendChild(author1);
		book1.appendChild(year1);
		book1.appendChild(price1);
		//Ϊbook�ڵ��������id
		book1.setAttribute("id", "1");
		//��book�ڵ���뵽bookStore�ڵ���
		bookStore.appendChild(book1);
		name2.setTextContent("���˽�̽��");
		year2.setTextContent("2016");
		author2.setTextContent("��˼��");
		price2.setTextContent("VIP��Ա���");
		book2.appendChild(name2);
		book2.appendChild(author2);
		book2.appendChild(year2);
		book2.appendChild(price2);
		//��ӵڶ���book
		bookStore.appendChild(book2);
		//�ѽڵ�bookStore���뵽document��
		document.appendChild(bookStore);
		//ʹ��TransformerFactory
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer();
			//����ڵ�֮��Ļ���
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			//����document�����������xml�ļ�
			transformer.transform(new DOMSource(document), new StreamResult(new File("bookCreate.xml")));
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ����������
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestDom testDom = new TestDom();
		testDom.domParse();
		testDom.creatXml();
	}
}
