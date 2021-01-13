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
        String command = request.getParameter("command");
        String name = request.getParameter("name");
        
        //解説処理
        if(command.compareTo("open") == 0) {
            int result = bank.open(name);
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
        else if(command.compareTo("close") == 0) {
            int result = bank.close(name);
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
                    +       "<h1>解約成功</h1>"
                    +        "<h3>" + name + "様の口座解約に成功しました。今までご利用ありがとうございました。</h3>"
                    +        "<a class=\"ok\" href=\"index.html\">戻る</a>" 
                    +    "</div>"
                    + "</body>"
                    + "</html>"
                );
            }
            else if(result == -1) {
                pw.println(
                    "<!DOCTYPE html>"
                    + "<html>"  
                    + "<head>"  
                    +    "<link rel=\"stylesheet\" href=\"static/account_input.css\">"  
                    +    "<meta charset=\"UTF-8\">"
                    + "</head>"
                    + "<body>"
                    +    "<div class=\"main\">"
                    +       "<h1>解約失敗</h1>"
                    +        "<h3>残高が0でないため" + name + "様の口座解約に失敗しました。すべての残高をお引き出しの上もう一度お試しください。</h3>"
                    +        "<a class=\"ok\" href=\"index.html\">戻る</a>" 
                    +    "</div>"
                    + "</body>"
                    + "</html>"
                );
            }
            else if(result == -7) {
                pw.println(
                    "<!DOCTYPE html>"
                    + "<html>"  
                    + "<head>"  
                    +    "<link rel=\"stylesheet\" href=\"static/account_input.css\">"  
                    +    "<meta charset=\"UTF-8\">"
                    + "</head>"
                    + "<body>"
                    +    "<div class=\"main\">"
                    +       "<h1>解約失敗</h1>"
                    +        "<h3>存在しない口座が指定されたため" + name + "様の口座解約に失敗しました。口座名をご確認の上もう一度お試しください。</h3>"
                    +        "<a class=\"ok\" href=\"index.html\">戻る</a>" 
                    +    "</div>"
                    + "</body>"
                    + "</html>"
                );
            }
        }
        else if(command.compareTo("deposit") == 0) {
            pw.println("deposit");
        }
        else if(command.compareTo("withdraw") == 0) {
            pw.println("withdraw");
        }
        else if(command.compareTo("balance") == 0) {
            pw.println("balance");
        }

    } 
 }