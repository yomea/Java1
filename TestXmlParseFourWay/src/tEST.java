import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class tEST {
	public static void main(String[] agrs) throws IOException{
		File srcFile = new File("D:\\java-workspace\\TankWar1.9.8net\\src");
		File destFile = new File("D:\\javaio\\TankWarAllCode3.txt");
		copyFile(srcFile, destFile);
		
	}
	
	
	
	
	public static void copyFile(File srcFile, File destFile) throws IOException {
		if(!srcFile.exists()) {
			throw new IllegalArgumentException("文件不存在");
		}
		
		File[] fe = srcFile.listFiles();
		for (File file : fe) {
			if(file.isDirectory()) {
				copyFile(file,destFile);
			}else {
				/*FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(destFile,true);*/
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(destFile,true));
				int i = 0;
				/*byte[] buf = new byte[8*1024];
				while((i = fis.read(buf,0,buf.length))!=-1) {
					
					fos.write(buf);
				}*/
				
				/*while((i = fis.read()) != -1) {
					fos.write(i);
				}*/
				
				char[] buf = new char[8*1024];
				while((i = isr.read(buf,0,buf.length))!=-1) {
					
					osw.write(buf);
				}
			}
		}
		
		
		
		
	}
}
