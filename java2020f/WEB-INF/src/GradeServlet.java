import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GradeServlet extends HttpServlet {
    private Student student;  //student's grade
    public GradeServlet() {
        student=new Student();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException  {

        String command = request.getParameter("command");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head><title>OnlineGradeReport</title></head>");
        pw.println("<body>");
        
        if(command.equals("take")) {
            String name = request.getParameter("name");
            String unit = request.getParameter("unit");
            String point = request.getParameter("point");
            student.take(name,toInt(unit),toInt(point));
            pw.println("新規科目の登録に成功しました．");
        
        } else if(command.equals("list")){
            String[] subs = student.list();
            for(String str : subs) {
                pw.println(str);
            }
        
        } else if(command.equals("gpa")){
            float gpa = student.getGPA();
            pw.println(gpa);
        
        } else {
            pw.println("指定された処理が不正です．");
        }

        pw.println("<a href='student.html'>戻る</a>");
        pw.println("</body>");
        pw.println("</html>");


    }
    
    public int toInt(String gaku) {
        int amount;
        try {
            amount = Integer.parseInt(gaku);
        } catch(NumberFormatException e) {
            amount=-1;
        }
        return amount;
    }
}