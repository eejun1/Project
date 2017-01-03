package item.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import item.model.service.ItemManageService;
import item.controller.Controller;
import item.dto.ForwardDTO;
import member.dto.MemberDTO;

public class DeleteItemServlet extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	public DeleteItemServlet() {
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
		String itemSeq = request.getParameter("itemSeq");  // 폼에서 넘어온 itemseq 값
		ForwardDTO forwardDTO = new ForwardDTO("");

		if (!((memberDTO.getAdmin()).equals("1"))) { // 접속 세션 관리자 계정 검사
			request.setAttribute("error_message", "오류 발생 : 권한이 없습니다! ADMIN 전용 페이지입니다");
			forwardDTO = new ForwardDTO("/error.jsp");
		}
		if ((memberDTO.getAdmin()).equals("1")) {
			try {
				ItemManageService itemManageService = ItemManageService.getInstance();
				itemManageService.deleteItemByItemSeq(itemSeq);
				forwardDTO.setUrl("/Item/item_remove_success.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : DB 접속 과정에서 오류가 발생했습니다.");
				forwardDTO.setUrl("/error.jsp");
			}
		}

		return forwardDTO;
	}

}
