package board.servlet;

import board.controller.Controller;
import board.dto.ForwardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        try {
            execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }
    @Override
    public ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ForwardDTO forwardDTO = new ForwardDTO("");
        return forwardDTO;
    }
}
