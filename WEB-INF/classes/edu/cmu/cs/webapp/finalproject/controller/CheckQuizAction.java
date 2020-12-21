package edu.cmu.cs.webapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.QuestionBean;
import edu.cmu.cs.webapp.finalproject.databean.QuizInfoBean;

import edu.cmu.cs.webapp.finalproject.databean.User;
import edu.cmu.cs.webapp.finalproject.model.Model;
import edu.cmu.cs.webapp.finalproject.model.QuizInfoDAO;
import edu.cmu.cs.webapp.finalproject.model.UserDAO;
import edu.cmu.cs.webapp.finalproject.model.QuestionDAO;

public class CheckQuizAction extends Action {    
    private UserDAO userDAO;
    private QuizInfoDAO quizInfoDAO;
    private QuestionDAO questionDAO;

    public CheckQuizAction(Model model) {
        userDAO = model.getUserDAO();
        quizInfoDAO = model.getQuizInfoDAO();
        questionDAO = model.getQuestionDAO();
    }

    public String getName() {
        return "check-quiz.do";
    }

    public String performPost(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        try {
            String visitor = request.getParameter("userName");
            
            request.setAttribute("users", userDAO.getItems(u.getUserName()));
            request.setAttribute("user", userDAO.getItem(visitor));

            int id = Integer.parseInt(request.getParameter("id"));
            QuestionBean[] qa = questionDAO.getItems(id);
            
            String[] questions = request.getParameterValues("question");
            String[] answers = request.getParameterValues("answer");
            
            int score = 0;
            for (int i=0; i<qa.length; i++) {
                for (int j=0; j<questions.length; j++) {
                    if (qa[i].getQuestion().equals( questions[j] )) {
                        if (qa[i].getAnswer().equalsIgnoreCase( answers[j] )) {
                            score++;
                        }
                    }
                }
            }

            QuizInfoBean bean = new QuizInfoBean();
            bean.setQuizId(id);
            bean.setUserName(u.getUserName());
            bean.setCreater(visitor);
            bean.setScore(score);
            quizInfoDAO.add(bean);

            return Action.perform("visitorshow.do", request);

        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
    
    public String performGet(HttpServletRequest request) {
        return Action.perform("prof.do", request);
    }
}
