package board.servlet;

import board.controller.Controller;
import board.dto.BoardDTO;
import board.dto.ForwardDTO;
import board.model.dao.BoardDAO;
import member.dto.MemberDTO;

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
 * Created by lee on 2017-01-03.
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet implements Controller{

    private static final long serialVersionUID = 1L;

    public BoardServlet(){
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
            System.out.println(e);
        }

    }
    @Override
    public ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("EUC-KR");   //한글 출력 설정 안해주면 깨짐
        HttpSession session = request.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        ForwardDTO forwardDTO = new ForwardDTO("");
        int userSeq = Integer.parseInt(memberDTO.getUserSeq());


        if(memberDTO==null){
            request.setAttribute("error_message","비로그인 접근입니다.");
            response.sendRedirect("/error.jsp");
        }

        BoardDTO boardDTO = new BoardDTO(request.getParameter("qnabdtitle"), request.getParameter("qnabdcontent"),
                Integer.parseInt(request.getParameter("qnabdpw")),userSeq);

        try {
            BoardDAO.getInstance().insertBoard(boardDTO);

           // response.sendRedirect("/QnaBoard/qnaboard.jsp");
            //forwardDTO.setUrl("/GetAllBoardServlet");
            ArrayList<BoardDTO> list = BoardDAO.getInstance().selectAllBoard();
            request.setAttribute("board_list",list);
            forwardDTO.setUrl("/QnaBoard/qnaboard.jsp");
        } catch (SQLException e) {

            request.setAttribute("error_message","오류 발생"+e.getErrorCode()+"에러가 발생했습니다");
            response.sendRedirect("/error.jsp");
            e.printStackTrace();
        }
        return forwardDTO;

    }

}
