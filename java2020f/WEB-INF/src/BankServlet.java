// 316025 卯木 優大
// SampleServlet.java

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BankServlet extends HttpServlet {
    private Bank bank; /* 口座の管理をするオブジェクト */

    public BankServlet() {  /* bankを初期化する */
        bank = new Bank();
    } 
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
                throws IOException, ServletException { 
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        String name = request.getParameter("account");
        int result = Bank.open(name);

        if(result == 0) {
            pw.println(
                "<!DOCTYPE html>"
                + "<html>"  
                + "<head>"  
                +    "<link rel=\"stylesheet\" href=\"static/account_input.css\">"  
                +    "<meta charset=\"UTF-8\">"
                + "</head>"
                + "<body>"
                +    "<div class=\"main\">"
                +       "<h1>開設成功</h1>"
                +        "<h3>" + name + "様の口座開設に成功しました。トップページにてお取引が行えます。</h3>"
                +        "<a class=\"ok\" href=\"index.html\">戻る</a>" 
                +    "</div>"
                + "</body>"
                + "</html>"
            );
        } else { 
            pw.println(
                "<!DOCTYPE html>"
                + "<html>"  
                + "<head>"  
                +    "<link rel=\"stylesheet\" href=\"static/account_input.css\">"  
                +    "<meta charset=\"UTF-8\">"
                + "</head>"
                + "<body>"
                +    "<div class=\"main\">"
                +       "<h1>開設失敗</h1>"
                +        "<h3>" + name + "様の口座開設に失敗しました。カスタマーセンターまでお問合せください。</h3>"
                +        "<a class=\"ok\" href=\"index.html\">戻る</a>" 
                +    "</div>"
                + "</body>"
                + "</html>"
            );
        }
    } 
 }