package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Handler클래스에 공통적으로 선언해야하는 인터페이스

public interface CommandHandler {
	public String process(HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception;
}
