import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class TestRandomAccessFile {
	
	public static void main(String[] agrs) throws FileNotFoundException {
		testRandomAccessFile();
	}
	
	private static void testRandomAccessFile() throws FileNotFoundException {
		RandomAccessFile raf = null;
		try {
			File f = new File("test");
		
		if(!f.exists()) {
			f.mkdir();
		}else {
			File f2 = new File(f,"test.dat");
			if(!f2.exists()) {
				
					f2.createNewFile();
					
			
				
					
			}
			raf = new RandomAccessFile(f2,"rw");
			raf.write('A');
//			raf.seek(0);
//			System.out.println((char)raf.read());
			raf.write('B');
//			System.out.print((char)raf.read());
			
			String s = "ÖÐ»ª";
			raf.write(s.getBytes("gbk"));
			byte[] byt = new byte[(int) raf.length()];
			raf.seek(0);
			raf.read(byt);
			
			for (byte b : byt) {
				System.out.println(Integer.toHexString(b & 0xff));
			}
			
			System.out.println(Arrays.toString(byt));
			String sg = new String(byt,"GBK");
			System.out.println(sg);
			
			
		}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
