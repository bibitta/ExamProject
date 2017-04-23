package com.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Friends {
	private List<UserBean> friends;

	/**
	 * 
	 */
	public Friends() {
		super();
	}

	/**
	 * @return the friends
	 */
	@XmlElement
	public List<UserBean> getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(List<UserBean> friends) {
		this.friends = friends;
	}

}
