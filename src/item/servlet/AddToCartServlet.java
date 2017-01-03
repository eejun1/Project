package item.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import item.controller.Controller;
import item.dto.CartDTO;
import item.dto.ForwardDTO;
import item.exception.DuplicatedItemException;
import item.model.service.ItemManageService;
import member.dto.MemberDTO;

public class AddToCartServlet extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	public AddToCartServlet() {
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
		ForwardDTO forwardDTO = new ForwardDTO("");
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");

		System.out.println(memberDTO);

		if (memberDTO == null) {  // 비회원
			request.setAttribute("error_message", "오류 발생 : 장바구니 기능은 로그인 후 사용 가능합니다.");
			forwardDTO.setUrl("/error.jsp");
		} else {  // 로그인 사용자
			try {
				ItemManageService itemManageService = ItemManageService.getInstance();
				CartDTO cartDTO = new CartDTO(request.getParameter("itemSeqInModal"), memberDTO.getUserSeq(),
						request.getParameter("requestItemQuantityInModal"));
				itemManageService.addToCart(cartDTO);
				forwardDTO.setUrl("/Cart/cart_add_success.jsp");
			} catch (DuplicatedItemException e) { // cart 테이블에서 userseq, itemseq 모두 존재하는 경우
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : 장바구니에 이미 존재하는 상품입니다. 장바구니 페이지에서 수정 가능합니다.");
				forwardDTO.setUrl("/error.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error_message", "오류 발생 : DB 접속 과정에서 오류가 발생했습니다.");
				forwardDTO.setUrl("/error.jsp");
			}
		}
		return forwardDTO;
	}
}