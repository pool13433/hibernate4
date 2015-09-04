package com.tss.poolsawatservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tss.poolsawatservice.dao.UserDao;
import com.tss.poolsawatservice.model.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDao _userDao;

	@RequestMapping(value = "/")
	public List getAll() {
		List<User> users = null;
		try {
			users = new ArrayList<User>();
			users = _userDao.getAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return users;
	}

	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public String delete(long id) {
		try {
			User user = new User(id);
			_userDao.delete(user);
		} catch (Exception ex) {
			return "{\"message\" :\""+ex.getMessage()+"\"}";
		}
		return "{\"message\" :\"User succesfully saved!\"}";
	}

	@RequestMapping(value = "/get-by-email")
	public String getByEmail(String email) {
		String userId;
		try {
			User user = _userDao.getByEmail(email);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}

	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	public User getid(@PathVariable Long id) {
		User user = new User();
		try {
			if (id != null) {
				user = _userDao.getById(id);
				System.out.println(" user ::==" + user.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String create(User user) {
		try {
			//String name = request.getParameter("name");
			//String email = //request.getParameter("email");
			//User user = new User(email, name);
			System.out.println("user ::=="+user.toString());
			_userDao.save(user);
		} catch (Exception ex) {
			return "{\"message\" :\""+ex.getMessage()+"\"}";
		}
		return "{\"message\" :\"User succesfully saved!\"}";
	}

} // class UserController