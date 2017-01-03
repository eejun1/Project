package member.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.controller.Controller;
import member.dto.ForwardDTO;
import member.dto.MemberDTO;
import member.model.service.MemberManageService;

public class GetAllMemberInfoServlet extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	public GetAllMemberInfoServlet() {
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
			System.out.println(e);
		}
	}

	@Override
	public ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
		ForwardDTO forwardDTO = new ForwardDTO("");

		if (!((memberDTO.getAdmin()).equals("1"))) { // 접속 세션 관리자 계정 검사
			request.setAttribute("error_message", "오류 발생 : 권한이 없습니다! ADMIN 전용 페이지입니다");
			forwardDTO.setUrl("/error.jsp");
		} else {
			try {
				MemberManageService memberManageService = MemberManageService.getInstance();
				ArrayList<MemberDTO> list = memberManageService.getMemberList();
				request.setAttribute("member_list", list);
				forwardDTO.setUrl("/Member/member_list.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : DB 접속 과정에서 오류가 발생했습니다.");
				forwardDTO.setUrl("/error.jsp");
			}
		}
		return forwardDTO;
	}
}