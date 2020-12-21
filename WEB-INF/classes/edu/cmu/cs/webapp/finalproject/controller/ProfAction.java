package edu.cmu.cs.webapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.User;
import edu.cmu.cs.webapp.finalproject.model.Model;
import edu.cmu.cs.webapp.finalproject.model.QuizDAO;
import edu.cmu.cs.webapp.finalproject.model.UserDAO;

public class ProfAction extends Action {    
    private QuizDAO quizDAO;
    private UserDAO userDAO;
    
    public ProfAction(Model model) {
        quizDAO = model.getQuizDAO();
        userDAO = model.getUserDAO();
    }
    
    public String getName() {
        return "prof.do";
    }
    
    public String performGet(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        try {
            request.setAttribute("users", userDAO.getItems(u.getUserName()));
            request.setAttribute("user", u);

       		request.setAttribute("quizzes", quizDAO.getItems(u.getUserName()));
            // request.setAttribute("form",  new ItemForm());
       		return ("prof.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
    
    public String performPost(HttpServletRequest request) {
        return performGet(request);
    }
}
