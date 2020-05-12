package controller;

import controller.action.*;

public class ActionFactory {
	
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {
		super();
	}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		    System.out.println("ActionFactory  :" + command);
	    //메인페이지
	    if (command==null) {
	     action=new MainAction();	
	    }
	    //동혁
	    else if (command.equals("login")) {
		  action = new LoginAction();
		} else if (command.equals("join")) {
		  action = new JoinAction();
		}
	    //지영
	    
	    //태우
	    
	    //준
	    
	    //나진
		
	    return action;
    }
}