package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.beans.FriendRequest;
import com.beans.FriendRequestInformation;
import com.beans.FriendRequestInformations;
import com.beans.Friends;
import com.beans.UserBean;
import com.database.ABaseRepo;
import com.database.FriendRequestRepo;
import com.database.JpaUserRepo;

/**
 * Servlet implementation class FriendRequestServlet
 */
@WebServlet("/FriendRequestServlet")
public class FriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FriendRequestServlet() {

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
			if (null != friendId) {

				final ABaseRepo<UserBean> repo = new JpaUserRepo();
				final UserBean friend = repo.find(Integer.parseInt(friendId));
				final String message = request.getParameter("message");
				final FriendRequest fRequest = new FriendRequest();
				fRequest.setRequester(user);
				fRequest.setFriend(friend);
				fRequest.setMessage(message);
				final ABaseRepo<FriendRequest> fRepo = new FriendRequestRepo();
				if (fRepo.insert(fRequest)) {
					response.getWriter().print("success");
				}
			} else {
				final FriendRequestRepo frepo = new FriendRequestRepo();
				final List<FriendRequest> requests = frepo.findrequests(user);
				final List<FriendRequestInformation> infoList = new ArrayList<>();
				for (final FriendRequest req : requests) {
					final UserBean requester = req.getRequester();
					infoList.add(
							new FriendRequestInformation(requester.getUsername(), req.getMessage(), requester.getId()));

				}
				final FriendRequestInformations infos = new FriendRequestInformations();
				infos.setRequests(infoList);
				JAXBContext context;
				try {
					context = JAXBContext.newInstance(FriendRequestInformations.class, FriendRequestInformation.class);
					Marshaller marshaller = context.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");

					final PrintWriter writer = response.getWriter();
					marshaller.marshal(infos, writer);
					marshaller.marshal(infos, System.out);
				} catch (JAXBException e) {

					e.printStackTrace();
				}
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
