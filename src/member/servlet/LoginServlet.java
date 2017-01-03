package member.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.controller.Controller;
import member.dto.ForwardDTO;
import member.dto.MemberDTO;
import member.model.service.MemberManageService;

public class LoginServlet extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String userpw = request.getParameter("userpw");
		MemberManageService memberManageService = MemberManageService.getInstance();
		MemberDTO memberDTO = memberManageService.selectMemberByEmail(email);
		ForwardDTO forwardDTO = new ForwardDTO("");
		if (!(memberDTO == null)) {
			if (userpw.equals(memberDTO.getUserpw())) {
				session.setAttribute("memberDTO", memberDTO);
				forwardDTO.setUrl("/Member/member_login_success.jsp");
			} else {
				request.setAttribute("error_message", "오류 발생 : 비밀번호가 일치하지 않습니다.");
				forwardDTO.setUrl("/error.jsp");
			}
		} else {
			request.setAttribute("error_message", "오류 발생 : 존재하지 않는 회원 정보입니다.");
			forwardDTO.setUrl("/error.jsp");
		}
		return forwardDTO;
	}
}