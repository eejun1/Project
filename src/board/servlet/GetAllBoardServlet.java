package board.servlet;

import board.dto.BoardDTO;
import board.dto.ForwardDTO;
import board.model.dao.BoardDAO;
import board.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by lee on 2017-01-06.
 */
@WebServlet("/GetAllBoardServlet")
public class GetAllBoardServlet extends HttpServlet implements Controller{

    private static final long serialVersionUID =1L;


    public GetAllBoardServlet(){
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

//        HttpSession session = request.getSession();

        ForwardDTO forwardDTO = new ForwardDTO("");

        try {
            ArrayList<BoardDTO> list = BoardDAO.getInstance().selectAllBoard();
            request.setAttribute("board_list",list);
            forwardDTO.setUrl("/QnaBoard/qnaboard.jsp");
            //response.sendRedirect("/QnaBoard/qnaboard.jsp");
        } catch (SQLException e) {
            request.setAttribute("error_message","오류 발생"+e.getErrorCode()+"에러가 발생했습니다");
            forwardDTO.setUrl("/error.jsp");
            //response.sendRedirect("/error.jsp");
            e.printStackTrace();
        }
        return forwardDTO;
    }


}
