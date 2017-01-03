package item.exception;

public class DuplicatedItemException extends Exception{

	// cart 테이블 중복 사용자 이름 && 상품 예외 처리
	private static final long serialVersionUID = 1L;

	DuplicatedItemException() {
	}

	public DuplicatedItemException(String msg) {
		super(msg);
	}
}
