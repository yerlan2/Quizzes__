package edu.cmu.cs.webapp.finalproject.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.User;

public class UserDAO extends GenericDAO<User> {
	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(User.class, tableName, cp);
	}

	public User[] getItems() throws RollbackException {
		User[] users = match();
		return users;
	}
    public User[] getItems(String s) throws RollbackException {
		User[] users = match(MatchArg.notEquals("userName", s));
		return users;
	}
    public User getItem(String s) throws RollbackException {
		User[] users = match(MatchArg.equals("userName", s));
		return users[0];
	}
}
