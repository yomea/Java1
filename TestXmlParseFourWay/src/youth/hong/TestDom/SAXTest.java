package youth.hong.TestDom;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.helpers.AttributesImpl;

import youth.hong.Handler.SAXHandler;

public class SAXTest {
	
	public List<Book> parseXml() {
		SAXHandler saxhandler = null;
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = parserFactory.newSAXParser();
			saxhandler = new SAXHandler();
			saxParser.parse("src/TestJDom/TestDom.xml", saxhandler);
			for (Book book : saxhandler.getBookList()) {
				System.out.println(book);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		};
		return saxhandler.getBookList();
		
	}
	/**
	 * ����xml�ļ�
	 */
	public void createXml() {
		List<Book> bookList = this.parseXml();
		//ת������
		SAXTransformerFactory tff = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
		try {
			TransformerHandler handler = tff.newTransformerHandler();
			//ת����
			Transformer tf = handler.getTransformer();
			//�����������ΪUTF-8
			tf.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			//�����ʽ����
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			//Ҫ������ļ�
			File file = new File("src/TestJDom/newBook.xml");
			//����ļ������ھʹ���һ��
			if(!file.exists()) {
				file.createNewFile();
			}
			Result result = new StreamResult(new FileOutputStream(file));
			//������result�������ټ������ݲ���Ч
			handler.setResult(result);
			handler.startDocument();
			AttributesImpl atts = new AttributesImpl();
			handler.startElement("", "", "bookStore", atts);
			//���������鼮�����Ǽ��뵽xml��
			for(Book book : bookList) {
				atts.clear();
				/**
				 * public void addAttribute(String uri,
                         String localName,�����ռ䱾����
                         String qName,Ԫ����
                         String type,Ԫ������
                         String value)ֵ
				 */
				atts.addAttribute("", "", "id", null, book.getId());
				atts.addAttribute("", "", "class", null, book.getClassName());
				//ע�ⰴ��SAXģʽ����xml�ļ���˳��ȥ�����ǩ
				handler.startElement("", "", "book", atts);
				atts.clear();
				handler.startElement("", "", "name", atts);
				char[] ch = book.getName().toCharArray();
				handler.characters(ch, 0, ch.length);
				handler.endElement("", "", "name");
				
				handler.startElement("", "", "author", atts);
				ch = book.getAuthor().toCharArray();
				handler.characters(ch, 0, ch.length);
				handler.endElement("", "", "author");
				
				handler.startElement("", "", "year", atts);
				ch = book.getYear().toCharArray();
				handler.characters(ch, 0, ch.length);
				handler.endElement("", "", "year");
				
				handler.endElement("", "", "book");
			}
			
			handler.endElement("", "", "bookStore");
			handler.endDocument();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public static void main(String[] args) {
		SAXTest saxTest = new SAXTest();
		
		saxTest.createXml();
		//saxTest.parseXml();
	}
}
