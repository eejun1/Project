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

public class RemoveMemberServlet extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	public RemoveMemberServlet() {
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
		ForwardDTO forwardDTO = new ForwardDTO("");

		if (memberDTO.equals(null)) {
			request.setAttribute("error_message", "오류 발생 : 로그인 정보와 세션 정보가 일치하지 않습니다. 다시 로그인 후 시도하세요.");
			forwardDTO.setUrl("/error.jsp");
		} else if ((memberDTO.getAdmin()).equals("1")) {
			request.setAttribute("error_message", "오류 발생 : 관리자 계정은 탈퇴가 불가능합니다.");
			forwardDTO.setUrl("/error.jsp");
		} else if ((memberDTO.getAdmin()).equals("0")) {
			try {
				MemberManageService memberManageService = MemberManageService.getInstance();
				memberManageService.removeMemberByEmail((String) (memberDTO.getEmail()));
				session.invalidate();
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