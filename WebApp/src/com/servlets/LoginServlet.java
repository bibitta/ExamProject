package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.UserBean;
import com.database.DbConnection;
import com.database.JpaUserRepo;
import com.database.UserDao;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final String userName = request.getParameter("username");
		final String password = request.getParameter("password");
		final String registerUserName = request.getParameter("register_userName");

		final JpaUserRepo repo = new JpaUserRepo();
		if (null != userName) {
			final UserBean user = repo.findUser(userName, password);
			if (null != user) {
				request.setAttribute("user", user);
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("profile.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else if (null != registerUserName) {
			final String password1 = request.getParameter("register_password1");
			final String password2 = request.getParameter("register_password2");
			final String email = request.getParameter("email");
			if (null != password1 && password1.equals(password2) && null != email) {
				UserBean user = new UserBean();
				user.setUsername(registerUserName);
				user.setPassword(password1);
				user.setEmail(email);

				if (repo.insert(user)) {
					request.setAttribute("user", user);
					request.getSession().setAttribute("user", user);
					request.getRequestDispatcher("profile.jsp").forward(request, response);

				} else {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
