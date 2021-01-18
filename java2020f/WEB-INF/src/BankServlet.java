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

  public BankServlet() { /* bankを初期化する */
    bank = new Bank();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter pw = response.getWriter();
    String command = request.getParameter("command");
    String name = request.getParameter("name");
    int result;

    //開設処理
    if (command.compareTo("open") == 0) {
      result = bank.open(name);
      if (result == 0) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>開設成功</h1>"
            + "<h3>" + name +
            "様の口座開設に成功しました。トップページにてお取引が行えます。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      } else {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>開設失敗</h1>"
            + "<h3>すでに同名の口座が存在するため" + name +
            "様の口座開設に失敗しました。<br>口座名を変更の上もう一度お試しください。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      }
    } else if (command.compareTo("close") == 0) {
      result = bank.close(name);
      if (result == 0) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>解約成功</h1>"
            + "<h3>" + name +
            "様の口座解約に成功しました。今までご利用ありがとうございました。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      } else if (result == -1) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>解約失敗</h1>"
            + "<h3>残高が0でないため" + name +
            "様の口座解約に失敗しました。<br>すべての残高をお引き出しの上もう一度お試しください。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      } else if (result == -7) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>解約失敗</h1>"
            + "<h3>存在しない口座が指定されたため" + name +
            "様の口座解約に失敗しました。<br>口座名をご確認の上もう一度お試しください。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      }
    } else if (command.compareTo("deposit") == 0) {

      String amount = request.getParameter("amount");
      result = bank.deposit(name, amount);

      if (result == 0) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>預金成功</h1>"
            + "<h3>" + name + "様の口座へ" + amount +
            "円 の預金に成功しました。</h3>"
            + "<h3>現在の残高は" + bank.showBalance(name) + "円 です。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      } else if (result == -3) {
        pw.println("<!DOCTYPE html>"
                   + "<html>"
                   + "<head>"
                   +
                   "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
                   + "<meta charset=\"UTF-8\">"
                   + "</head>"
                   + "<body>"
                   + "<div class=\"main\">"
                   + "<h1>預金失敗</h1>"
                   + "<h3>" + name + "様の口座へ " + amount +
                   "円 の預金に失敗しました。</h3>"
                   + "<h3>0円以下の金額を預金することはできません。</h3>"
                   + "<a class=\"ok\" href=\"index.html\">戻る</a>"
                   + "</div>"
                   + "</body>"
                   + "</html>");
      } else if (result == -4) {
        pw.println("<!DOCTYPE html>"
                   + "<html>"
                   + "<head>"
                   +
                   "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
                   + "<meta charset=\"UTF-8\">"
                   + "</head>"
                   + "<body>"
                   + "<div class=\"main\">"
                   + "<h1>預金失敗</h1>"
                   + "<h3>" + name + "様の口座へ " + amount +
                   "円 の預金に失敗しました。金額は整数でご入力ください。</h3>"
                   + "<a class=\"ok\" href=\"index.html\">戻る</a>"
                   + "</div>"
                   + "</body>"
                   + "</html>");
      } else if (result == -7) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>預金</h1>"
            + "<h3>存在しない口座が指定されたため、<br>" + name + "様の口座へ " + amount + "円 の預金に失敗しました。<br>口座名をご確認のうえもう一度お試しください。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      }
    } else if (command.compareTo("withdraw") == 0) {

      String amount = request.getParameter("amount");
      result = bank.withdraw(name, amount);

      if (result == 0) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>引き出し成功</h1>"
            + "<h3>" + name + "様の口座から " + amount +
            "円 の引き出しに成功しました。</h3>"
            + "<h3>現在の残高は" + bank.showBalance(name) + "円 です。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      } else if (result == -1) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>引き出し失敗</h1>"
            + "<h3>" + name + "様の口座から " + amount +
            "円 の引き出しに失敗しました。</h3>"
            + "<h3>現在の残高を超える金額を引き出しすることはできません。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      } else if (result == -3) {
        pw.println("<!DOCTYPE html>"
                   + "<html>"
                   + "<head>"
                   +
                   "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
                   + "<meta charset=\"UTF-8\">"
                   + "</head>"
                   + "<body>"
                   + "<div class=\"main\">"
                   + "<h1>引き出し失敗</h1>"
                   + "<h3>" + name + "様の口座から " + amount +
                   "円 の引き出しに失敗しました。</h3>"
                   + "<h3>0円以下の金額を引き出しすることはできません。</h3>"
                   + "<a class=\"ok\" href=\"index.html\">戻る</a>"
                   + "</div>"
                   + "</body>"
                   + "</html>");
      } else if (result == -4) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>引き出し失敗</h1>"
            + "<h3>" + name + "様の口座から " + amount +
            "円 の引き出しに失敗しました。金額は整数でご入力ください。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      } else if (result == -7) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>引き出し失敗</h1>"
            + "<h3>存在しない口座が指定されたため、<br>" + name +
            "様の口座から " + amount +
            "円 の引き出しに失敗しました。<br>口座名をご確認のうえもう一度お試しください。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      }
    } else if (command.compareTo("balance") == 0) {

      result = bank.showBalance(name);

      if (result == -7) {
        pw.println(
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
            + "<meta charset=\"UTF-8\">"
            + "</head>"
            + "<body>"
            + "<div class=\"main\">"
            + "<h1>照会失敗</h1>"
            + "<h3>存在しない口座が指定されたため、" + name +
            "様の口座残高の照会に失敗しました。<br>口座名をご確認のうえもう一度お試しください。</h3>"
            + "<a class=\"ok\" href=\"index.html\">戻る</a>"
            + "</div>"
            + "</body>"
            + "</html>");
      } else {
        pw.println("<!DOCTYPE html>"
                   + "<html>"
                   + "<head>"
                   +
                   "<link rel=\"stylesheet\" href=\"static/account_input.css\">"
                   + "<meta charset=\"UTF-8\">"
                   + "</head>"
                   + "<body>"
                   + "<div class=\"main\">"
                   + "<h1>照会成功</h1>"
                   + "<h3>照会に成功しました。" + name + "様の口座残高は " +
                   bank.showBalance(name) + "円です。</h3>"
                   + "<a class=\"ok\" href=\"index.html\">戻る</a>"
                   + "</div>"
                   + "</body>"
                   + "</html>");
      }
    }
  }
}