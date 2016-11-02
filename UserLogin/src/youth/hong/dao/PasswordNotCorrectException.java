package youth.hong.dao;

public class PasswordNotCorrectException extends Exception {
	public PasswordNotCorrectException(String str) {
		super(str);
	}
}
