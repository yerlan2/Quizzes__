package edu.cmu.cs.webapp.finalproject.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private UserDAO userDAO;
	private QuizDAO quizDAO;
	private QuizInfoDAO quizInfoDAO;
	private QuestionDAO questionDAO;
	// private ItemDAO itemDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			userDAO  	= new UserDAO(pool, "project_users");
			quizDAO 	= new QuizDAO(pool, "project_quizzes");
			quizInfoDAO	= new QuizInfoDAO(pool, "project_quiz_info");
			questionDAO = new QuestionDAO(pool, "project_questions");
			// itemDAO = new ItemDAO(pool, "todolist");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public UserDAO getUserDAO() { return userDAO; }
	public QuizDAO getQuizDAO() { return quizDAO; }
	public QuizInfoDAO getQuizInfoDAO() { return quizInfoDAO; }
	public QuestionDAO getQuestionDAO() { return questionDAO; }

	// public ItemDAO getItemDAO()  { return itemDAO; }
}
