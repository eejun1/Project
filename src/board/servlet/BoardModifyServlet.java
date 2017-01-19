package board.servlet;

import board.dto.BoardDTO;
import board.model.dao.BoardDAO;
import member.dto.MemberDTO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by lee on 2017-01-11.
 */
@WebServlet("/BoardModifyServlet")
public class BoardModifyServlet extends HttpServlet  {

    public BoardModifyServlet(){super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("EUC-KR");
        HttpSession session = request.getSession();
        int qnabdseq = Integer.parseInt(request.getParameter("qnabdseq"));
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");

        if(memberDTO == null){
            request.setAttribute("error_message","비로그인 접근입니다.");
            response.sendRedirect("/error.jsp");
        }
    BoardDTO boardDTO = new BoardDTO(request.getParameter("qnabdtitle"),Integer.parseInt(request.getParameter("qnabdpw")),request.getParameter("qnabdcontent"));

        ArrayList<BoardDTO> list = null;
        try {
            BoardDAO.getInstance().modifyBoard(boardDTO,qnabdseq);
            list = BoardDAO.getInstance().selectAllBoard();
        } catch (SQLException e) {
            request.setAttribute("error_message","오류 발생"+e.getErrorCode()+"에러가 발생했습니다");
            response.sendRedirect("/error.jsp");
            e.printStackTrace();
        }
        request.setAttribute("board_list",list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/QnaBoard/qnaboard.jsp");
        dispatcher.forward(request,response);

    }

}
