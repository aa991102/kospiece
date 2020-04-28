package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import model.dao.UserDAO;
import model.dto.UserVO;

public class JoinAction implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String url = "user/login.jsp"; 
    
    HttpSession session = request.getSession();
    
    UserVO userVO = new UserVO();
    
    userVO.setId(request.getParameter("id"));
    userVO.setPw(request.getParameter("pw"));
    userVO.setName(request.getParameter("name"));
    userVO.setEmail(request.getParameter("email1")+"@"+request.getParameter("email2"));
    userVO.setNickname(request.getParameter("nickname"));
    userVO.setPhone(request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3"));    
      
    session.setAttribute("id", request.getParameter("id"));
    
    UserDAO userDAO = UserDAO.getInstance();
    userDAO.insertUser(userVO);

    
    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
  }
}