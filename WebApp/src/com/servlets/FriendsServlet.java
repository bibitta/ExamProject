package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.beans.Friends;
import com.beans.UserBean;
import com.database.JpaUserRepo;

/**
 * Servlet implementation class FriendsServlet
 */
@WebServlet("/Friends")
public class FriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FriendsServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String userName = request.getParameter("search-friend");
		if (null != userName) {
			final JpaUserRepo repo = new JpaUserRepo();
			final List<UserBean> resultList = repo.findUsers(userName);
			sendXmlResponse(response, resultList);
		} else {
			final Object userObject = request.getSession().getAttribute("user");
			if (userObject instanceof UserBean) {
				final UserBean user = (UserBean) userObject;

				List<UserBean> resultList = user.getFriends();
				sendXmlResponse(response, resultList);
			}
		}
	}

	private void sendXmlResponse(HttpServletResponse response, final List<UserBean> resultList) throws IOException {
		final Friends friends = new Friends();
		friends.setFriends(resultList);
		try {
			JAXBContext context = JAXBContext.newInstance(Friends.class, UserBean.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");

			final PrintWriter writer = response.getWriter();
			marshaller.marshal(friends, writer);
			marshaller.marshal(friends, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
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
