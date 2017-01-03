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
import member.exception.DuplicatedUserNameException;
import member.model.service.MemberManageService;

public class ModifyMemberServlet extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	public ModifyMemberServlet() {
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
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
		ForwardDTO forwardDTO = new ForwardDTO("");
		if (memberDTO == null) {// 로그인 안된 경우
			request.setAttribute("error_message", "오류 발생 : 로그인 후 회원정보 수정이 가능합니다.");
			forwardDTO.setUrl("/error.jsp");

		} else {
			memberDTO = new MemberDTO(memberDTO.getUserSeq(), request.getParameter("modifyEmail"), request.getParameter("userName"),
					request.getParameter("userpw"),
					request.getParameter("address"), request.getParameter("phoneNumber"),
					memberDTO.getAdmin());
			try {

				MemberManageService memberManageService = MemberManageService.getInstance();

				memberManageService.modifyMember(memberDTO);
				session.setAttribute("memberDTO", memberDTO);
				forwardDTO.setUrl("/Member/member_modify_success.jsp");

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : DB 접속 중 오류가 발생했습니다.");
				forwardDTO.setUrl("/error.jsp");
			} catch (DuplicatedUserNameException e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : 이미 존재하는 Username입니다.");
				forwardDTO.setUrl("/error.jsp");
			}
		}
		return forwardDTO;
	}
}