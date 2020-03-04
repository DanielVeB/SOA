package hello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hello")
public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("windows-1250");

        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        out.println("<html>");
        out.println("<head><title>First servlet</title></head>");
        out.println("<body>");
        out.println("<p> Witaj " + name + "</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
