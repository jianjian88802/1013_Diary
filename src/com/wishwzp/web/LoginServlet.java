package com.wishwzp.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wishwzp.dao.UserDao;
import com.wishwzp.model.User;
import com.wishwzp.util.DbUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DbUtil dbutil = new DbUtil();
	UserDao userDao = new UserDao();
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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		HttpSession session = request.getSession();
		Connection con =null;
		try {
			con=dbutil.getCon();
			User user=new User(username,password);
			User currentUser=userDao.login(con, user);
			if (currentUser == null) {
				request.setAttribute("user", user);
				request.setAttribute("error", "用户名或密码错误！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				if("remember-me".equals(remember)){
					rememberMe(username,password,response);
				}
				session.setAttribute("currentUser", currentUser);
				request.getRequestDispatcher("main").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 记住密码
	 * @param userName
	 * @param password
	 * @param response
	 */
	private void rememberMe(String username,String password,HttpServletResponse response){
		Cookie user=new Cookie("user",username+"-"+password);
		user.setMaxAge(1*60*60*24*7);
		response.addCookie(user);
	}
}
