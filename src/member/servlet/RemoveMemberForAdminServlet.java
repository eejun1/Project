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


public class RemoveMemberForAdminServlet extends HttpServlet implements Controller {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoveMemberForAdminServlet() {
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
		}
	}

	@Override
	public ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
		String userSeq = request.getParameter("userSeq");
		ForwardDTO forwardDTO = new ForwardDTO("");

		if (((memberDTO.getAdmin())).equals("1")) {
			try {
				MemberManageService memberManageService = MemberManageService.getInstance();
				session.setAttribute("deletedEmail", request.getParameter("deletedEmail"));
				session.setAttribute("deletedUserName", request.getParameter("deletedUserName"));
				memberManageService.removeMemberByUserSeq(userSeq);
				forwardDTO.setUrl("/Member/member_remove_success.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : DB 접속 과정에서 오류가 발생했습니다.");
				forwardDTO.setUrl("/error.jsp");
			}
		}

		return forwardDTO;
	}
}
