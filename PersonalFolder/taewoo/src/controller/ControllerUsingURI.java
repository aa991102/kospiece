package controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;
import controller.command.NullHandler;

//해당요청의 담당 컨트롤러파일로 연결
public class ControllerUsingURI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    private Map<String, CommandHandler> commandHandlerMap = 
    		new HashMap<>();

    //프로퍼티파일을 불러오는 초기생성자(어플리케이션 시작시 한번만 호출됨)
    public void init() throws ServletException {
        String configFile = getInitParameter("configFile");
        
        Properties prop = new Properties();
        
        String configFilePath = getServletContext().getRealPath(configFile);
        
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        
        Iterator keyIter = prop.keySet().iterator();
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next();
            String handlerClassName = prop.getProperty(command);
            try {
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance = 
                        (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
            } catch (ClassNotFoundException | InstantiationException 
            		| IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }

    //get방식 요청시 호출되는 서비스함수
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    //Post방식 요청시 호출되는 서비스함수
    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    	
    	//클라이언트가 요청한 명령을 파악
		String command = request.getRequestURI();
		
		//전체URI에서 ContextPath를 제거
		if (command.indexOf(request.getContextPath()) == 0) {
			//순수한 요청URI만 추출
			command = command.substring(request.getContextPath().length());
		}
		
		//담당 컨트롤러파일을 가져와 handler변수에 저장한다
        CommandHandler handler = commandHandlerMap.get(command);
        
        if (handler == null) {//담당handler가 없는 경우
            handler = new NullHandler(); //
        }
        
        //담당handler가 있는 경우
        String viewPage = null;
        try {
            viewPage = handler.process(request, response); //담당 handler의 process함수 실행
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        
        //view페이지로 forwading하기
        if (viewPage != null) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	        dispatcher.forward(request, response);
        }
    }
}




