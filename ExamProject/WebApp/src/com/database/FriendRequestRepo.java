package com.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.beans.FriendRequest;
import com.beans.UserBean;

public class FriendRequestRepo extends ABaseRepo<FriendRequest> {

	public FriendRequestRepo() {
		super(FriendRequest.class);
	}
	

	public List<FriendRequest> findrequests(final UserBean user) {
		final EntityManager em = getEntityManager();
		List<FriendRequest> result = null;
		try {
			final String sql = "SELECT u FROM FriendRequest u WHERE " + "u.friend =:friend";
			TypedQuery<FriendRequest> typedQuery = em.createQuery(sql, FriendRequest.class);
			typedQuery.setParameter("friend", user);
			result = typedQuery.getResultList();
		} finally {
			em.close();
		}
		return result;
	}

}
