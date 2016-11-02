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
	//������־������ʼ
	@Override
	public void startDocument() throws SAXException {
		
		System.out.println("������ʼ");
	}
	
	//������־��������
	@Override
	public void endDocument() throws SAXException {
		System.out.println("��������");
	}
	
	//��������Ԫ��
	//��ʼ��ǩ
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(qName.equals("book")) {
			bookIndex++;
			book = new Book();
			System.out.println("============================��" + bookIndex + "���鿪ʼ��=========================");
			//��֪book������Ԫ�ص�����
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
				System.out.println("��" + bookIndex + "�����" + attributes.getQName(i) + "��:" + attributes.getValue(i)/*attributes.getValue(Qname)*/);
			}
		}else if(!qName.equals("bookStore")) {
			System.out.print("��" + bookIndex + "�����" + qName + "��:");
		}
		
	}
	//������ǩ�ڵ�����
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		value = new String(ch,start,length);
		//�����ǩ��û�����ݣ���bookstore��book֮���û�����ݣ�������ӡ
		if(!value.trim().equals("")) {
			System.out.println(value);

		}
		
	}
	
	//����Ԫ�صĽ�����ǩ
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("book")) {
			bookList.add(book);
			book = null;
			System.out.println("============================��" + bookIndex + "���������=========================");
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
