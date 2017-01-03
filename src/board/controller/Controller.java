package board.controller;

        import board.dto.ForwardDTO;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

public interface Controller {
    ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
