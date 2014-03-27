package foo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/pingfoobar")
public class Ping extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public Ping() {
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getOutputStream().print("PONG");
	}

}
