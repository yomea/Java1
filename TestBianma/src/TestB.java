
public class TestB {

	public static void main(String[] args) {
		String s = "∫Í∏ÁHTC";
		byte[] by = s.getBytes();
		for(byte bt : by) {
			System.out.print(Integer.toHexString(bt & 0xff)+"  ");
		}

	}

}
