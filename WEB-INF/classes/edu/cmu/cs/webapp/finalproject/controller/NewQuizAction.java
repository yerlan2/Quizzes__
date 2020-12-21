package edu.cmu.cs.webapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.User;
import edu.cmu.cs.webapp.finalproject.formbean.QuestionForm;
import edu.cmu.cs.webapp.finalproject.model.Model;
import edu.cmu.cs.webapp.finalproject.model.UserDAO;

public class NewQuizAction extends Action {
    private UserDAO userDAO;
    
    public NewQuizAction(Model model) {
        userDAO = model.getUserDAO();
    }
    
    public String getName() {
        return "new-quiz.do";
    }
    
    public String performGet(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        try {
            request.setAttribute("users", userDAO.getItems( u.getUserName() ));
            request.setAttribute("user", u);
            
            request.setAttribute("number", request.getParameter("qNumber"));
            request.setAttribute("form", new QuestionForm());
            return ("new-quiz.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
    
    public String performPost(HttpServletRequest request) {
        return performGet(request);
    }
}
