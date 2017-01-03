package item.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import item.dto.ItemDTO;
import item.model.service.ItemManageService;
import member.dto.MemberDTO;
import item.controller.Controller;
import item.dto.ForwardDTO;

public class GetAllItemInfoServlet extends HttpServlet implements Controller {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GetAllItemInfoServlet() {
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
		ForwardDTO forwardDTO = new ForwardDTO("");
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");

		System.out.println(memberDTO);

		if (memberDTO != null) {  // null 값이 아니면 로그인 한 사용자
			if ((memberDTO.getAdmin()).equals("1")) { // 관리자
				forwardDTO.setUrl("/Item/itemboard_admin.jsp");
			}
			if ((memberDTO.getAdmin()).equals("0")) { // 일반 사용자
				forwardDTO.setUrl("/Item/itemboard.jsp");
			}
		} else {  // 비회원
			forwardDTO.setUrl("/Item/itemboard.jsp");
		}
		try {
			ItemManageService itemManageService = ItemManageService.getInstance();
			ArrayList<ItemDTO> itemList = itemManageService.getItemList();
			request.setAttribute("item_list", itemList);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_message", "오류 발생 : DB 접속 과정에서 오류가 발생했습니다.");
			forwardDTO.setUrl("/error.jsp");
		}
		return forwardDTO;
	}

}
