package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CodeCount {
	static int commentCount;
	static int codeCount;
	static int spaceCount;
	static int total;
	

	
	public static void main(String[] agrs) throws IOException {
		File f = new File("D:\\java-workspace\\TankWar1.9.8net\\src");
		File[] childs = null;
		if(f.isDirectory()) {
		childs = f.listFiles();
		}
		
		BufferedReader br = null;
		for(File child : childs) {
			if(child.getName().matches(".*.java$")){
			try {
				br = new BufferedReader(new FileReader(child));
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			String s = "";
			
			while((s = br.readLine()) != null) {
				Pattern comment = Pattern.compile("[\\s]*[/*]+[[^\\d]|[\\d]]*");
				
				Pattern space = Pattern.compile("[\\s]*");
				Matcher m = comment.matcher(s);
				
				Matcher m2 = space.matcher(s);
				//System.out.println(m.matches());
				if(m.matches()) {
					commentCount++;
				}
				
				

				if(m2.matches()) {
					spaceCount++;
				}

				//System.out.println(s);
				total++;
				
				}
			}
		}
		
		
		System.out.println("Comment count:"+commentCount+"\n"+"Code count:"+(total-commentCount-spaceCount)+"\n"+"WhiteSpace count:"+spaceCount);
		
		
	}
}
