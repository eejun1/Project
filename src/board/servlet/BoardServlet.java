package board.servlet;

import board.dto.BoardDTO;
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
import java.util.List;

/**
 * Created by lee on 2017-01-03.
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet{

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

             response.setContentType("text/plain;charset=UTF-8");

           HttpSession session = request.getSession();
           MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
           int userSeq = Integer.parseInt(memberDTO.getUserSeq());

            System.out.println(userSeq);



        BoardDTO boardDTO = new BoardDTO(request.getParameter("qnabdtitle"), request.getParameter("qnabdcontent"),
                Integer.parseInt(request.getParameter("qnabdpw")),userSeq);

        try {
            BoardDAO.getInstance().insertBoard(boardDTO);
            response.sendRedirect("/QnaBoard/qnaboard.jsp");
        } catch (SQLException e) {
            System.out.println("에러 발생");
            e.printStackTrace();
        }


    }

}
