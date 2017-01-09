package board.controller;

import board.dto.ForwardDTO;
import board.servlet.BoardServlet;
import board.servlet.GetAllBoardServlet;
import board.servlet.ViewBoardServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BoardFrontController")
public class BoardFrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("EUC-KR");

		String userRequest = request.getParameter("userRequest"); // 파라미터에서 값
																	// 받아오기
		System.out.println(userRequest); // 리퀘스트 콘솔 출력

		ForwardDTO forwardDTO = new ForwardDTO(""); // ForwardDTO 객체, 서블릿 처리 후 리디렉트 경로 담는 용도
		Controller controller = null; // 컨트롤러 객체 생성

		//리퀘스트 요청은 name = "userRequest"

		switch (userRequest) { // 사용자 요청에 따라서 서블릿 호출
			case "GetAllBoardServlet":
				controller = new GetAllBoardServlet();
				break;

			case "BoardServlet":
				controller = new BoardServlet();
				break;

			case "ViewBoardServlet":
				controller = new ViewBoardServlet();
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
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(forwardDTO.getUrl());
			requestDispatcher.forward(request, response);
		}


	}
}
