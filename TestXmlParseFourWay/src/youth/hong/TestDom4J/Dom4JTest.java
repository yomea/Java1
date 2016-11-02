package youth.hong.TestDom4J;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import youth.hong.TestDom.Book;

public class Dom4JTest {
	
	private static List<Book> bookList = new ArrayList<Book>();
	//dom4j����xml�ĵ�
	public void parseXML() {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new File("src/TestJDom/TestDom.xml"));
			System.out.println(new File("src/TestJDom/TestDom.xml"));
			Element bookStore = document.getRootElement();
			Iterator<Element> it = bookStore.elementIterator();
			while(it.hasNext()) {
				System.out.println("===================ĳ���鿪ʼ==================");
				Book bookEntity = new Book();
				Element book = it.next();
				List<Attribute> attrList = book.attributes();
				System.out.println("��ǰ����½ڵ�ĸ����������հ׵��ı��ڵ㣩" + book.nodeCount());
				for(Attribute attribute : attrList) {
					System.out.println("��������" + attribute.getName() + "����ֵ:" + attribute.getValue());
					if(attribute.getName().equals("id")) {
						bookEntity.setId(attribute.getValue());
					}
					else if(attribute.getName().equals("class")) {
						bookEntity.setClassName(attribute.getValue());
					}
				}
				Iterator<Element> itt = book.elementIterator();
				while(itt.hasNext()) {
					Element childNode = itt.next();
					System.out.println("�ڵ���:" + childNode.getName() + "�ڵ�ֵ:" + childNode.getStringValue());
					if(childNode.getName().equals("name")) {
						bookEntity.setName(childNode.getStringValue());
					}
					else if(childNode.getName().equals("author")) {
						bookEntity.setAuthor(childNode.getStringValue());
					}
					else if(childNode.getName().equals("year")) {
						bookEntity.setYear(childNode.getStringValue());
					}
					
				}
				bookList.add(bookEntity);
				bookEntity = null;
				System.out.println("===================ĳ�������==================");
				System.out.println(bookList.size());
			}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//����XML�ĵ�
	
	public void createXML() {
		Document document = DocumentHelper.createDocument();
		Element rss = document.addElement("rss");
		rss.addAttribute("version", "2.0");
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("<!--����Ҫ��-->");
		Element image = channel.addElement("image");
		Element imageTitle = image.addElement("title");
		imageTitle.setText("news.baidu.com");
		Element link = image.addElement("link");
		link.setText("http://news.baidu.com");
		Element url = image.addElement("url");
		url.setText("http://img.baidu.com/img/logo-news.gif");
		File file = new File("src/TestJDom/news.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
			writer.setEscapeText(false);
			writer.write(document);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Dom4JTest d4j = new Dom4JTest();
		d4j.parseXML();
		d4j.createXML();

	}

}
