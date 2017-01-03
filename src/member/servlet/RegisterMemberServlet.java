package member.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.controller.Controller;
import member.dto.ForwardDTO;
import member.dto.MemberDTO;
import member.exception.DuplicatedEmailException;
import member.exception.DuplicatedUserNameException;
import member.model.service.MemberManageService;

public class RegisterMemberServlet extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	public RegisterMemberServlet() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}

	@Override
	public ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
		ForwardDTO forwardDTO = new ForwardDTO("");

		if (memberDTO != null) {
			request.setAttribute("error_message", "로그인 상태에서는 회원 가입이 불가능합니다.");
			forwardDTO.setUrl("/error.jsp");
		} else {
			memberDTO = new MemberDTO(request.getParameter("signUpEmail"), request.getParameter("userpw"),
					request.getParameter("userName"), request.getParameter("address"),
					request.getParameter("phoneNumber"));
			try {
				MemberManageService memberManageService = MemberManageService.getInstance();
				memberManageService.registerMember(memberDTO);
				memberDTO = memberManageService.selectMemberByEmail(memberDTO.getEmail());
				session.setAttribute("memberDTO", memberDTO);
				forwardDTO.setUrl("/Member/member_register_success.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : DB 접속 과정에서 오류가 발생했습니다.");
				forwardDTO.setUrl("/error.jsp");
			} catch (DuplicatedEmailException e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : 이미 존재하는 EMAIL입니다.");
				forwardDTO.setUrl("/error.jsp");
			} catch (DuplicatedUserNameException e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : 이미 존재하는 USERNAME입니다.");
				forwardDTO.setUrl("/error.jsp");
			}
		}
		return forwardDTO;
	}
}