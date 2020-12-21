package edu.cmu.cs.webapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.User;
import edu.cmu.cs.webapp.finalproject.model.Model;
import edu.cmu.cs.webapp.finalproject.model.QuizDAO;
import edu.cmu.cs.webapp.finalproject.model.QuizInfoDAO;
import edu.cmu.cs.webapp.finalproject.model.UserDAO;

public class VisitorShowAction extends Action {
    private QuizDAO quizDAO;
    private QuizInfoDAO quizInfoDAO;
    private UserDAO userDAO;

    public VisitorShowAction(Model model) {
        quizDAO = model.getQuizDAO();
        quizInfoDAO = model.getQuizInfoDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "visitorshow.do";
    }
    
    public String performPost(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        try {
            String visitor = request.getParameter("userName");

            request.setAttribute("quizInfo", quizInfoDAO.getItems(u.getUserName(), visitor));
            request.setAttribute("users", userDAO.getItems(u.getUserName()));
            request.setAttribute("user", userDAO.getItem(visitor));
            request.setAttribute("quizzes", quizDAO.getItems(visitor));
            return ("visitor.jsp");

        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }

    public String performGet(HttpServletRequest request) {
        return performGet(request);
    }
}