package member.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.dto.ForwardDTO;
import member.servlet.GetAllMemberInfoServlet;
import member.servlet.LoginServlet;
import member.servlet.LogoutServlet;
import member.servlet.ModifyMemberServlet;
import member.servlet.RegisterMemberServlet;
import member.servlet.RemoveMemberForAdminServlet;
import member.servlet.RemoveMemberServlet;

public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("EUC-KR");
		
		String userRequest = request.getParameter("userRequest"); // 파라미터에서 값
																	// 받아오기

		System.out.println(userRequest); // 리퀘스트 콘솔 출력
		ForwardDTO forwardDTO = new ForwardDTO(""); // ForwardDTO 객체, 서블릿 처리 후 리디렉트 경로 담는 용도
		Controller controller = null; // 컨트롤러 객체 생성
		
		switch (userRequest) { // 사용자 요청에 따라서 서블릿 호출
			case "LoginServlet":
				controller = new LoginServlet();
				break;
			case "LogoutServlet":
				controller = new LogoutServlet();
				break;
			case "ModifyMemberServlet":
				controller = new ModifyMemberServlet();
				break;
			case "RegisterMemberServlet":
				controller = new RegisterMemberServlet();
				break;
			case "RemoveMemberServlet":
				controller = new RemoveMemberServlet();
				break;
			case "getAllMemberInfoServlet":
				controller = new GetAllMemberInfoServlet();
				break;
			case "RemoveMemberForAdminServlet":
				controller = new RemoveMemberForAdminServlet();
				break;
		}	
		
		
		try {
			forwardDTO = controller.execute(request, response); // 요청 수행 후 리디렉트
																// 경로 리턴
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_message", "에러 caught in MemberFrontController");
			forwardDTO.setUrl("/error.jsp"); // 에러 발생시 error페이지 리디렉트
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