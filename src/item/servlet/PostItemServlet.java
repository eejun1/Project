package item.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import item.controller.Controller;
import item.dto.ForwardDTO;
import item.dto.ItemDTO;
import item.model.service.ItemManageService;
import member.dto.MemberDTO;

public class PostItemServlet extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	public PostItemServlet() {
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

		if (memberDTO.getAdmin().equals("0")) {
			request.setAttribute("error_message", "관리자 로그인 후 상품 등록이 가능합니다.");
			forwardDTO.setUrl("/error.jsp");
		} else {
			try {
				ItemManageService itemManageService = ItemManageService.getInstance();
				ItemDTO itemDTO = new ItemDTO("1", request.getParameter("postItemName"),
						request.getParameter("postItemPrice"), request.getParameter("postItemCategory"), "1",
						request.getParameter("postItemQuantity"), request.getParameter("postItemTitleSrc"),
						request.getParameter("postItemContent"), request.getParameter("postItemSrc"));
				itemManageService.postItem(itemDTO);
				forwardDTO.setUrl("/Item/item_post_success.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : DB 접속 과정에서 오류가 발생했습니다.");
				forwardDTO.setUrl("/error.jsp");
			}
		}
		return forwardDTO;
	}
}
