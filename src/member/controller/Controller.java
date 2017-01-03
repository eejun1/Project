package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.dto.ForwardDTO;

// 모든 컨트롤러 클래스에서 동일한 메소드 구현
public interface Controller {
	public ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}