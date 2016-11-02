public class HelloWorld {
    public static void main(String[] args) {
		// 创建一个空的StringBuilder对象
		StringBuilder str = new StringBuilder();
        
		// 追加字符串
		str.append("jaewkjldfxmopzdm");
		
        // 从后往前每隔三位插入逗号
		
        int k = 4;
        int i=3;
       
        while(true) {
        	
        	k=str.length();
        	int h = k-i;
        	if(h<0) break;
            str.insert(h,",");
           
            i=i+4;
            
        }
        
		
        // 将StringBuilder对象转换为String对象并输出
		System.out.print(str.toString());
	}
}