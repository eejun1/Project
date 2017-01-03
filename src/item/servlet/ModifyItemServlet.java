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

public class ModifyItemServlet extends HttpServlet implements Controller {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModifyItemServlet() {
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
		ItemManageService itemManageService = ItemManageService.getInstance();
		ForwardDTO forwardDTO = new ForwardDTO("");
		if ((memberDTO.getAdmin()).equals("0") || memberDTO == null) {
			request.setAttribute("error_message", "오류 발생 : 관리자 계정 로그인 후 상품 수정이 가능합니다.");
			forwardDTO.setUrl("/error.jsp");
		} else {
			ItemDTO itemDTO = new ItemDTO(request.getParameter("itemSeqInModal"), request.getParameter("itemNameInModal"),
					request.getParameter("itemPriceInModal"), request.getParameter("itemCategoryInModal"), "1",
					request.getParameter("itemQuantityInModal"), request.getParameter("itemTitleSrcInModal"),
					request.getParameter("itemContentInModal"), request.getParameter("itemSrcInModal"));
			try {
				itemManageService.updateItemByItemSeq(itemDTO);
				forwardDTO.setUrl("/Item/item_modify_success.jsp");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);
				request.setAttribute("error_message", "오류 발생 : DB 접속 중 오류가 발생했습니다.");
				forwardDTO.setUrl("/error.jsp");
			}
		}
		return forwardDTO;
	}

}
