package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.PostMessageBean;
import com.beans.UserBean;
import com.database.ABaseRepo;
import com.database.PostRepo;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String city = request.getParameter("city");
		final String message = request.getParameter("message");
		final String temp = request.getParameter("temp");
		final UserBean user = (UserBean) request.getSession().getAttribute("user");
		if (null != city && null != message && null != temp && null != user) {
			final PostMessageBean post = new PostMessageBean();
			post.setCity(city);
			post.setMessage(message);
			post.setTemp(Integer.parseInt(temp));
			post.setOwner(user);
			user.getPosts().add(post);
			final ABaseRepo<PostMessageBean> repo = new PostRepo();
			if(repo.insert(post)){
				//System.out.println(user.getPosts().size());
				//request.setAttribute("post", post);
				//request.getRequestDispatcher("home.jsp").forward(request, response);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("success");
			}else{
				
			}
		} else {
			// error message
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
