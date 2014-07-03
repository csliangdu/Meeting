package com.dajiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dajiao.dao.NotificationDAO;
import com.dajiao.model.Person;
import com.dajiao.model.User;
import com.dajiao.service.LoginService;
import com.dajiao.service.NotificationService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
	    System.out.println("user : " + username + " ; password : " + password + " ;");
		
		Person person = null;
		
		if((person = LoginService.validate(username, password)) != null){
			// load notifications
			// request.getSession().setAttribute("notifyList", NotificationService.getInviteMessage(username));
			System.out.println("useraccount: " + ((User)person).getaccount());
			request.setAttribute("notifyList", NotificationService.getInviteMessage(((User)person).getaccount()));
			request.getSession().setAttribute("person", person);
			request.setAttribute("notinum", "");
			request.getRequestDispatcher("./myNotification.jsp").forward(request, response);
		    System.out.println("admin login success!");
		}else if(username != null && password != null){
			request.setAttribute("fail", "1");
			request.getRequestDispatcher("./meetingManager.jsp").forward(request, response);
		    System.out.println("admin login fail");
			// response.sendRedirect("./meetingManager.jsp?fail=1");
		}/*
		else {
			// refresh page 
			request.getRequestDispatcher("./myNotificaiton.jsp").forward(request, response);
		}*/
		
	}

}
