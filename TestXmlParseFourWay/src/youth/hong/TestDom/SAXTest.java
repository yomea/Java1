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
	 * 生成xml文件
	 */
	public void createXml() {
		List<Book> bookList = this.parseXml();
		//转化工厂
		SAXTransformerFactory tff = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
		try {
			TransformerHandler handler = tff.newTransformerHandler();
			//转换器
			Transformer tf = handler.getTransformer();
			//设置输出编码为UTF-8
			tf.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			//输出格式缩进
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			//要输出的文件
			File file = new File("src/TestJDom/newBook.xml");
			//如果文件不存在就创建一个
			if(!file.exists()) {
				file.createNewFile();
			}
			Result result = new StreamResult(new FileOutputStream(file));
			//必须在result定义完再加入内容才有效
			handler.setResult(result);
			handler.startDocument();
			AttributesImpl atts = new AttributesImpl();
			handler.startElement("", "", "bookStore", atts);
			//遍历所有书籍将他们加入到xml中
			for(Book book : bookList) {
				atts.clear();
				/**
				 * public void addAttribute(String uri,
                         String localName,命名空间本地名
                         String qName,元素名
                         String type,元素类型
                         String value)值
				 */
				atts.addAttribute("", "", "id", null, book.getId());
				atts.addAttribute("", "", "class", null, book.getClassName());
				//注意按照SAX模式解析xml文件的顺序去定义标签
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
