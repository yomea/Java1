import java.io.*;
public class TestFile {

	public static void main(String[] args)throws IOException {
		TestFile tf = new TestFile();
		
		File f = new File("D:\\java\\NumberSort");
		
		tf.listAllFile(f);
	}
	
	public void listAllFile(File f)  throws IOException  {
		
		if(f.exists()) {
			File[] listFile = f.listFiles();
			for (File file : listFile) {
				if(file.isDirectory()) {
					this.listAllFile(file);
				}else {
					System.out.println(file);
				}
			}
			
		}else {
			//f.mkdirs();
			f.createNewFile();
		}
	}

}
