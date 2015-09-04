package com.tss.poolsawatservice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tss.poolsawatservice.model.User;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(User user) {
		getSession().saveOrUpdate(user);
	}

	public void delete(User user) {
		//getSession().delete(user);
		getSession().delete("User", user);
	}

	@SuppressWarnings("unchecked")
	public List getAll() {
		return getSession().createQuery("from User").list();
	}

	public User getByEmail(String email) {
		User user = (User) getSession()
				.createQuery("from User where email = :email")
				.setParameter("email", email).uniqueResult();
		System.out.println("user ::=='" + user.toString());
		return user;
	}

	public User getById(long id) {
		// return (User) getSession().load(User.class, id);
		return (User) getSession().createQuery("from User Where id = :id")
				.setParameter("id", id).uniqueResult();
	}

	public void update(User user) {
		getSession().update(user);
	}

} // class UserDao
