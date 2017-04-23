package com.beans;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "user")
@XmlRootElement
public class UserBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 50, nullable = false, unique = true)
	private String username;
	@Column(name = "email", length = 30, nullable = false)
	private String email;
	@Column(name = "picturePath", length = 50)
	private String picturePath;
	@Column(name = "password", length = 15, nullable = false)
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<PostMessageBean> posts;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userfriend", joinColumns = @JoinColumn(name = "user_id") , inverseJoinColumns = @JoinColumn(name = "friend_id") )
	private List<UserBean> friends;
	@ManyToMany(mappedBy = "friends", fetch = FetchType.EAGER)
	private List<UserBean> befriended;

	/**
	 * @param usernameArg
	 */
	public UserBean(final String usernameArg) {
		this.username = usernameArg;
	}

	public UserBean() {

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
	 * @return the email
	 */
	@XmlElement
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the picturePath
	 */
	@XmlElement
	public String getPicturePath() {
		return picturePath;
	}

	/**
	 * @param picturePath
	 *            the picturePath to set
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	/**
	 * @return the password
	 */
	@XmlElement
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * @return the posts
	 */
	@XmlTransient
	public Set<PostMessageBean> getPosts() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<PostMessageBean> posts) {
		this.posts = posts;
	}

	/**
	 * @return the friends
	 */
	public List<UserBean> getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(List<UserBean> friends) {
		this.friends = friends;
	}

	/**
	 * @return the befriended
	 */
	public List<UserBean> getBefriended() {
		return befriended;
	}

	/**
	 * @param befriended the befriended to set
	 */
	public void setBefriended(List<UserBean> befriended) {
		this.befriended = befriended;
	}

}
