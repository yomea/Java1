package youth.hong;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("[a-z[A-Z]]");
		String s = "a";
		Matcher m = p.matcher(s);
		System.out.println(m.matches());

	}

}
