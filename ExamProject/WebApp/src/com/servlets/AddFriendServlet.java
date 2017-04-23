package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.UserBean;
import com.database.ABaseRepo;
import com.database.JpaUserRepo;

/**
 * Servlet implementation class AddFriendServlet
 */
@WebServlet("/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFriendServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final Object userObject = request.getSession().getAttribute("user");
		if (userObject instanceof UserBean) {
			final UserBean user = (UserBean) userObject;
			final String friendId = request.getParameter("friend-id");
			final ABaseRepo<UserBean> repo = new JpaUserRepo();
			final UserBean friend = repo.find(Integer.parseInt(friendId));
			user.getFriends().add(friend);
			friend.getFriends().add(friend);
			if (repo.update(friend) && repo.update(user)) {
				response.getWriter().print("success");
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
