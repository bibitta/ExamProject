package com.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FriendRequestInformations {
	private List<FriendRequestInformation> requests;

	/**
	 * 
	 */
	public FriendRequestInformations() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the requests
	 */
	@XmlElement
	public List<FriendRequestInformation> getRequests() {
		return requests;
	}

	/**
	 * @param requests
	 *            the requests to set
	 */
	public void setRequests(List<FriendRequestInformation> requests) {
		this.requests = requests;
	}
}
