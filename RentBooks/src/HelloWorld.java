public class HelloWorld {
    public static void main(String[] args) {
		// ����һ���յ�StringBuilder����
		StringBuilder str = new StringBuilder();
        
		// ׷���ַ���
		str.append("jaewkjldfxmopzdm");
		
        // �Ӻ���ǰÿ����λ���붺��
		
        int k = 4;
        int i=3;
       
        while(true) {
        	
        	k=str.length();
        	int h = k-i;
        	if(h<0) break;
            str.insert(h,",");
           
            i=i+4;
            
        }
        
		
        // ��StringBuilder����ת��ΪString�������
		System.out.print(str.toString());
	}
}