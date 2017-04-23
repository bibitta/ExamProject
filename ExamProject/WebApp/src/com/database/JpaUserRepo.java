package com.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.beans.UserBean;

public class JpaUserRepo extends ABaseRepo<UserBean> {

	public JpaUserRepo() {
		super(UserBean.class);
	}

	public UserBean findUser(final String userName, final String password) {
		final EntityManager em = getEntityManager();
		final UserBean result;
		try {
			final String sql = "SELECT u FROM UserBean u WHERE " + "u.username =:userName and u.password =:password";
			TypedQuery<UserBean> typedQuery = em.createQuery(sql, UserBean.class);
			typedQuery.setParameter("userName", userName);
			typedQuery.setParameter("password", password);
			final List<UserBean> users = typedQuery.getResultList();
			if (users.isEmpty()) {
				result = null;
			} else if (1 == users.size()) {
				result = users.get(0);
			} else {
				result = null;
			}

		} finally {
			em.close();
		}

		return result;

	}


	public List<UserBean> findUsers(final String userName) {
		List<UserBean> result = null;
		final EntityManager em = getEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<UserBean> query = cb.createQuery(UserBean.class);
			Root<UserBean> root = query.from(UserBean.class);

			query.where(cb.like(root.<String> get("username"), cb.parameter(String.class, "userName")));
			TypedQuery<UserBean> typedQuery = em.createQuery(query);
			typedQuery.setParameter("userName", "%" + userName + "%");
			result = typedQuery.getResultList();
		} finally {
			em.close();
		}
		return result;
	}

}
