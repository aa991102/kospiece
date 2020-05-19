package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String command = request.getParameter("command"); //1.jsp에서 보내온 mode 파라미터를 받는다.
    System.out.println("servlet 요청 받음 : " + command);

    ActionFactory af = ActionFactory.getInstance();
    Action action = af.getAction(command); //2.ActionFactory의 getAction메서드를 이용하여 해당 mode에 맞는 action클래스 불러오기

    if (action != null) {
      action.execute(request, response);
    }//3.해당하는 action객체로 url 정보 전송
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response); //post방식으로 받아도 do get()메서드로 처리한다.
  }
}
