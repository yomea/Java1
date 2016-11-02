package youth.hong.Handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import youth.hong.TestDom.Book;

public class SAXHandler extends DefaultHandler{
	int bookIndex = 0;
	private List<Book> bookList = new ArrayList<Book>();
	private Book book = null;
	private String value = null;
	//用来标志解析开始
	@Override
	public void startDocument() throws SAXException {
		
		System.out.println("解析开始");
	}
	
	//用来标志解析结束
	@Override
	public void endDocument() throws SAXException {
		System.out.println("解析结束");
	}
	
	//用来遍历元素
	//开始标签
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(qName.equals("book")) {
			bookIndex++;
			book = new Book();
			System.out.println("============================第" + bookIndex + "本书开始了=========================");
			//已知book下属性元素的名称
			//System.out.println(attributes.getValue("id"));
			for(int i = 0; i < attributes.getLength(); i++) {
				String name = attributes.getQName(i);
				if(name.equals("id")) {
					book.setId(attributes.getValue(i));
				}else if(name.equals("class")) {
					book.setClassName(attributes.getValue(i));
				}
				book.setId(attributes.getValue(i));
				//System.out.println(name);
				System.out.println("第" + bookIndex + "本书的" + attributes.getQName(i) + "是:" + attributes.getValue(i)/*attributes.getValue(Qname)*/);
			}
		}else if(!qName.equals("bookStore")) {
			System.out.print("第" + bookIndex + "本书的" + qName + "是:");
		}
		
	}
	//遍历标签内的内容
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		value = new String(ch,start,length);
		//如果标签下没有内容，如bookstore与book之间就没有内容，将不打印
		if(!value.trim().equals("")) {
			System.out.println(value);

		}
		
	}
	
	//遍历元素的结束标签
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("book")) {
			bookList.add(book);
			book = null;
			System.out.println("============================第" + bookIndex + "本书结束了=========================");
		}
		else if(qName.equals("name")) {
			book.setName(value);
		}
		else if(qName.equals("author")) {
			book.setAuthor(value);
		}
		else if(qName.equals("year")) {
			book.setYear(value);
		}
		
	}
	
	

	public List<Book> getBookList() {
		return bookList;
	}
	
	
}
