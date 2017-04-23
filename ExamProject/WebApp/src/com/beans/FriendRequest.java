package com.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class FriendRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6564272409747642184L;
	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private UserBean requester;
	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private UserBean friend;
	@Column(name = "message")
	private String message;

	/**
	 * 
	 */
	public FriendRequest() {
	}

	/**
	 * @return the requester
	 */
	public UserBean getRequester() {
		return requester;
	}

	/**
	 * @param requester
	 *            the requester to set
	 */
	public void setRequester(UserBean requester) {
		this.requester = requester;
	}

	/**
	 * @return the friend
	 */
	public UserBean getFriend() {
		return friend;
	}

	/**
	 * @param friend
	 *            the friend to set
	 */
	public void setFriend(UserBean friend) {
		this.friend = friend;
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
}
