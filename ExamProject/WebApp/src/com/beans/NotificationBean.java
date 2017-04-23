package com.beans;

public class NotificationBean {
	private String message;
	private UserBean user;

	/**
	 * @param message
	 * @param user
	 */
	public NotificationBean(String message, UserBean user) {

		this.message = message;
		this.user = user;
	}

	public NotificationBean() {

	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the user
	 */
	public UserBean getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(UserBean user) {
		this.user = user;
	}

}
