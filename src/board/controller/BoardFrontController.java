package board.controller;

import member.controller.Controller;
import member.dto.ForwardDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BoardFrontController")
public class BoardFrontController {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userRequest = request.getParameter("userRequest"); // 파라미터에서 값
																	// 받아오기

		System.out.println(userRequest); // 리퀘스트 콘솔 출력

		Controller controller = null; // 컨트롤러 객체 생성

		//리퀘스트 요청 지정
//		switch (userRequest) { // 사용자 요청에 따라서 서블릿 호출
//			case "DeleteItemServlet":
//				controller = new DeleteItemServlet();
//				break;
//			case "GetAllItemInfoServlet":
//				controller = new GetAllItemInfoServlet();
//				break;
//			case "ModifyItemServlet":
//				controller = new ModifyItemServlet();
//				break;
//			case "PostItemServlet":
//				controller = new PostItemServlet();
//				break;
//			case "SearchItemServlet":
//				controller = new SearchItemServlet();
//				break;
//		}

		ForwardDTO forwardDTO = null; // ForwardDTO 객체, 서블릿 처리 후 리디렉트 경로 담는 용도

		try {
			forwardDTO = controller.execute(request, response); // 요청 수행 후 리디렉트
																// 경로 리턴
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			forwardDTO = new ForwardDTO("/error.jsp"); // 에러 발생시 error페이지 리디렉트
		}

		// 요청 처리 후 공통 작업, 리턴 받은 경로 리디렉트
		// sendRedirect, dispatcher 방식 모두 시도
		if (!forwardDTO.isRedirect()) {
			response.sendRedirect(forwardDTO.getUrl());
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(forwardDTO.getUrl());
			requestDispatcher.forward(request, response);
		}

	}
}
