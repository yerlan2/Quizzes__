package edu.cmu.cs.webapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.User;
import edu.cmu.cs.webapp.finalproject.model.Model;
import edu.cmu.cs.webapp.finalproject.model.QuizDAO;
import edu.cmu.cs.webapp.finalproject.model.QuizInfoDAO;
import edu.cmu.cs.webapp.finalproject.model.UserDAO;

public class QuizInfoAction extends Action {
    private QuizDAO quizDAO;
    private QuizInfoDAO quizInfoDAO;
    private UserDAO userDAO;


    public QuizInfoAction(Model model) {
        quizDAO = model.getQuizDAO();
        quizInfoDAO = model.getQuizInfoDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "quiz-info.do";
    }
    
    public String performPost(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        try {
            request.setAttribute("users", userDAO.getItems(u.getUserName()));
            request.setAttribute("user", u);

            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("quizInfo", quizInfoDAO.getItemsCreater(u.getUserName(), id));
            request.setAttribute("quiz", quizDAO.getItem(id));
            return ("quiz-info.jsp");

        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}
