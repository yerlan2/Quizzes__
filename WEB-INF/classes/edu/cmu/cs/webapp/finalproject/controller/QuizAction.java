package edu.cmu.cs.webapp.finalproject.controller;


import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.User;
import edu.cmu.cs.webapp.finalproject.model.Model;
import edu.cmu.cs.webapp.finalproject.model.QuestionDAO;
import edu.cmu.cs.webapp.finalproject.model.QuizDAO;
import edu.cmu.cs.webapp.finalproject.model.UserDAO;

public class QuizAction extends Action {
    private QuizDAO quizDAO;
    private QuestionDAO questionDAO;
    private UserDAO userDAO;

    public QuizAction(Model model) {
        questionDAO = model.getQuestionDAO();
        quizDAO = model.getQuizDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "quiz.do";
    }
    
    public String performPost(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        try {
            String visitor = request.getParameter("userName");
            int id = Integer.parseInt(request.getParameter("id"));
            
            request.setAttribute("quiz", quizDAO.getItem(id));
            request.setAttribute("questions", questionDAO.getItems(id));

            request.setAttribute("users", userDAO.getItems(u.getUserName()));
            request.setAttribute("user", userDAO.getItem(visitor));
            return ("quiz.jsp");

        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}