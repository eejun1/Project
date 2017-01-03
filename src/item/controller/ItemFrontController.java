package item.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.servlet.AddToCartServlet;
import item.servlet.DeleteItemServlet;
import item.servlet.GetAllItemInfoServlet;
import item.servlet.ModifyItemServlet;
import item.servlet.PostItemServlet;
import item.servlet.SearchItemServlet;
import item.dto.ForwardDTO;

public class ItemFrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("EUC-KR");

		// 리퀘스트 파라미터 값 받아오기
		String userRequest = (String) request.getParameter("userRequest");
		
		System.out.println(userRequest); // 리퀘스트 콘솔 출력
		ForwardDTO forwardDTO = new ForwardDTO("");  // DTO 객체 초기화
		Controller controller = null;  // 컨트롤러 객체 생성

		switch (userRequest) { // 사용자 요청에 따라서 서블릿 호출
			case "DeleteItemServlet":
				controller = new DeleteItemServlet();
				break;
			case "GetAllItemInfoServlet":
				controller = new GetAllItemInfoServlet();
				break;
			case "ModifyItemServlet":
				controller = new ModifyItemServlet();
				break;
			case "PostItemServlet":
				controller = new PostItemServlet();
				break;
			case "SearchItemServlet":
				controller = new SearchItemServlet();
				break;
			case "AddToCartServlet":
				controller = new AddToCartServlet();
				break;
		}
		try {
			// 요청 수행 후 리디렉트 경로 리턴
			forwardDTO = controller.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_message", "에러 caught in BoardFrontController");
			forwardDTO.setUrl("/error.jsp"); // 에러 발생시 error페이지 리디렉트
		}

		if (!forwardDTO.isRedirect()) {
			response.sendRedirect(forwardDTO.getUrl());
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(forwardDTO.getUrl());
			requestDispatcher.forward(request, response);
		}

	}
}
