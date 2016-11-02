package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg {
	public static void main(String[] agrs) {
		/*p("abc".matches("..."));
		p("asd346xfcgv".replaceAll("\\d", "-"));
		Pattern p = Pattern.compile("[a-z]{3}");
		Matcher m = p.matcher("abc");
		p(m.matches());
		p("aab".matches("a+"));
		p("".matches("a*"));
		p("b".matches("ba*"));
		p("b".matches("a*b"));
		p("d".matches("a?"));
		p("951645267@qq.com".matches("\\d{5,9}@\\w{2}\\.com"));
		p("asdf".matches(".+"));
		Pattern ss = Pattern.compile("\\d{3,5}");
		String s = "szdjghv123-34664-789-33";
		Matcher mm = ss.matcher(s);
		p(mm.matches());
		mm.reset();
		p(mm.find());
		p(mm.start()+"-"+mm.end());
		p(mm.find());
		p(mm.start()+"-"+mm.end());
		p(mm.find());
		p(mm.start()+"-"+mm.end());
		p(".".matches("[.]"));
		p("5".matches("."));*/
		String s = "123,45634-124,5678";
		Pattern p = Pattern.compile("(\\d+,)(\\d+)");
		Matcher m = p.matcher(s);
		m.find();
		StringBuffer sb = new StringBuffer();
		m.appendReplacement(sb, "a");
		System.out.println(sb+"----"+m.group());
		//m.find();
		//m.appendReplacement(sb, "a");
		//System.out.println(sb);
		m.appendTail(sb);
		System.out.println(sb+"-----"+m.group(2));
		
	}


public static void p(Object o) {
	System.out.println(o);
	}
}