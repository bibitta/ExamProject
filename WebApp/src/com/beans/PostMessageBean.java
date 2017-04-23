package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class PostMessageBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "message", length = 200, nullable = false)
	private String message;
	@Column(name = "city", length = 50, nullable = false)
	private String city;
	@Column(name = "temp", nullable = false)
	private int temp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private UserBean owner;

	/**
	 * Constructor
	 */
	public PostMessageBean() {

	}

	/**
	 * @return the id
	 */
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the temp
	 */
	public int getTemp() {
		return temp;
	}

	/**
	 * @param temp
	 *            the temp to set
	 */
	public void setTemp(int temp) {
		this.temp = temp;
	}

	/**
	 * @return the owner
	 */
	public UserBean getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(UserBean owner) {
		this.owner = owner;
	}

}
