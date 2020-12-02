import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet {
public void doGet(HttpServletRequest request,
    HttpServletResponse response)
    throws ServletException, IOException {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter pw = response.getWriter();
    pw.println("<HTML>");
    pw.println ("<HEAD><TITLE>sample</TITLE></HEAD>");
    pw.println ("<BODY>316025の卯木です</BODY>");
    pw.println ("</HTML>");

    }
} 