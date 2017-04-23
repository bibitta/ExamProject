package com.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FriendRequestInformation {
	
	private String username;
	
	private String message;
	
	private int id;

	/**
	 * 
	 */
	public FriendRequestInformation() {

	}

	/**
	 * @param username
	 * @param message
	 * @param id
	 */
	public FriendRequestInformation(String username, String message, int id) {
		super();
		this.username = username;
		this.message = message;
		this.id = id;
	}

	/**
	 * @return the username
	 */
	@XmlElement
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the message
	 */
	@XmlElement
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
	 * @return the id
	 */
	@XmlElement
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
