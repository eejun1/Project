package member.exception;

public class DuplicatedEmailException extends Exception {

	// 중복 이메일 예외 처리
	private static final long serialVersionUID = 1L;

	DuplicatedEmailException() {
	}

	public DuplicatedEmailException(String msg) {
		super(msg);
	}

}
