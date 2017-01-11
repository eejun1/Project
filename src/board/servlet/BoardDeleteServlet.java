package board.servlet;

import board.model.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by lee on 2017-01-06.
 */
@WebServlet("/BoardDeleteServlet")
public class BoardDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public BoardDeleteServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int qnabdseq = Integer.parseInt(request.getParameter("qnabdseq"));

        try {
            BoardDAO.getInstance().deleteBoard(qnabdseq);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(qnabdseq+"글삭제가 완료되었습니다");

        response.sendRedirect("/index.jsp");
    }


}
