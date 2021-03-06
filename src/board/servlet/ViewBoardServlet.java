package board.servlet;

import board.controller.Controller;
import board.dto.BoardDTO;
import board.dto.ForwardDTO;
import board.model.dao.BoardDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by lee on 2017-01-09.
 */
@WebServlet("/ViewBoardServlet")
public class ViewBoardServlet extends HttpServlet implements Controller{
    private static final long serialVersionUID =1L;

    public ViewBoardServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("EUC-KR");

        int qnabdseq = Integer.parseInt(request.getParameter("qnabdseq"));


        try {
            ArrayList<BoardDTO> list = BoardDAO.getInstance().viewBoard(qnabdseq);

            request.setAttribute("board_content",list);

//            response.sendRedirect("/QnaBoard/qnacontent.jsp");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/QnaBoard/qnacontent.jsp");
            dispatcher.forward(request,response);

        } catch (SQLException e) {
            request.setAttribute("error_message","오류 발생"+e.getErrorCode()+"에러가 발생했습니다");

            e.printStackTrace();
        }

    }
    @Override
    public ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ForwardDTO forwardDTO = new ForwardDTO("");


        return forwardDTO;
    }
}
