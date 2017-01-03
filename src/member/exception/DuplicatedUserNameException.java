package member.exception;

public class DuplicatedUserNameException extends Exception {

	// 중복 사용자 이름 예외 처리
	private static final long serialVersionUID = 1L;

	DuplicatedUserNameException() {
	}

	public DuplicatedUserNameException(String msg) {
		super(msg);
	}
}
