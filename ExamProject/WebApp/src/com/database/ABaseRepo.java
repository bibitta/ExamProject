package com.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.beans.UserBean;

public abstract class ABaseRepo<T> {

	private Class<T> typeParameterClass;

	protected EntityManager getEntityManager() {
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("UserPU");
		final EntityManager em = factory.createEntityManager();
		return em;
	}

	/**
	 * 
	 */
	public ABaseRepo(final Class<T> typeParameterClassArg) {
		typeParameterClass = typeParameterClassArg;
	}

	public boolean insert(final T entity) {
		boolean result = false;
		final EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			try {
				em.persist(entity);
				em.getTransaction().commit();
				result = true;
			} catch (final Exception e) {
				em.getTransaction().rollback();
				throw e;
			}
		} finally {
			em.close();
		}

		return result;
	}
	

	public boolean update(final T entity) {
		boolean result = false;
		final EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			try {
				em.merge(entity);
				em.getTransaction().commit();
				result = true;
			} catch (final Exception e) {
				em.getTransaction().rollback();
				throw e;
			}
		} finally {
			em.close();
		}

		return result;
	}
	
	public boolean remove(final T entity) {
		boolean result = false;
		final EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			try {
				em.remove(entity);
				em.getTransaction().commit();
				result = true;
			} catch (final Exception e) {
				em.getTransaction().rollback();
				throw e;
			}
		} finally {
			em.close();
		}
		
		return result;
	}

	/**
	 * ksjdfhskjdkjsdfhksjdf
	 * 
	 * @param id
	 * @return
	 */
	public T find(final int id) {
		T result = null;
		final EntityManager em = getEntityManager();
		try {
			result = em.find(typeParameterClass, id);
		} finally {
			em.close();
		}
		return result;
	}

}
