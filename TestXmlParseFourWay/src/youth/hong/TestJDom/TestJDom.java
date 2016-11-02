package youth.hong.TestJDom;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import youth.hong.TestDom.Book;

public class TestJDom {

	private static List<Book> bookList = new ArrayList<Book>();
	
	public void parseXML() {
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			Document document = saxBuilder
					.build(new InputStreamReader(new FileInputStream("src/TestJDom/TestDom.xml"),"utf-8"));
			/*Document document2 = saxBuilder
					.build(new FileReader("src/TestJDom/TestDom.xml"));*///û���ַ����룬�������ʹ����������
			Element element = document.getRootElement();
			int i = 0;
			// elment.getAttributeValue()����һ��list����
			List<Element> bookList = element.getChildren();
			// ����ÿ��book������
			for (Element book : bookList) {
				Book bookEntity = new Book();
				i++;
				System.out.println("======================��" + i + "��ʼ==========================");
				// ��֪������
				// System.out.println(book.getAttributeValue("class"));
				List<Attribute> attrList = book.getAttributes();
				for (Attribute attribute : attrList) {
					System.out.println("�������ƣ�" + attribute.getName() + "----����ֵ�ǣ�" + attribute.getValue());
					if(attribute.getName().equals("id")) {
						bookEntity.setId(attribute.getValue());
					}
					else if(attribute.getName().equals("class")) {
						bookEntity.setClassName(attribute.getValue());
					}
				}
				List<Element> children = book.getChildren();
				for (Element element2 : children) {
					System.out.println("�ڵ���--" + element2.getName() + "--�ڵ�ֵ--" + element2.getValue());
					if(element2.getName().equals("name")) {
						bookEntity.setName(element2.getValue());
					}
					else if(element2.getName().equals("author")) {
						bookEntity.setAuthor(element2.getValue());
					}
					else if(element2.getName().equals("year")) {
						bookEntity.setYear(element2.getValue());
					}
				}
				System.out.println("======================��" + i + "����==========================");
				TestJDom.bookList.add(bookEntity);
				bookEntity = null;
				
				
			}
//			System.out.println(TestJDom.bookList.size());
		} catch (JDOMException | IOException e) {

			e.printStackTrace();
		}

	}
	
	public void createXML() {
		//����һ��root�ڵ�rss
		Element rss = new Element("rss");
		//����version����
		rss.setAttribute("version","2.0");
		//����document
		Document document = new Document(rss);
		
		Element channel = new Element("channel");
		rss.addContent(channel);
		Element title = new Element("title");
		
		channel.addContent(title);
		
		title.setText("<java��ã�����>");
		//title.addContent(new CDATA("<!--java��ã�����-->"));
		Format format = Format.getCompactFormat();
		format.setIndent("");
		format.setEncoding("gbk");
		XMLOutputter outputer = new XMLOutputter(format);
		try {
			
			outputer.output(document, new FileOutputStream("src/TestJDom/JDomnews.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TestJDom jd = new TestJDom();
		jd.parseXML();
		jd.createXML();
	}

}
