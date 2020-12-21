package edu.cmu.cs.webapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.QuestionBean;
import edu.cmu.cs.webapp.finalproject.databean.QuizBean;

// import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.User;
import edu.cmu.cs.webapp.finalproject.formbean.QuestionForm;
import edu.cmu.cs.webapp.finalproject.formbean.QuizForm;
import edu.cmu.cs.webapp.finalproject.model.Model;
import edu.cmu.cs.webapp.finalproject.model.QuizDAO;
import edu.cmu.cs.webapp.finalproject.model.UserDAO;
import edu.cmu.cs.webapp.finalproject.model.QuestionDAO;

public class CreateQuizAction extends Action {    
    private FormBeanFactory<QuizForm> formBeanFactoryQuiz = new FormBeanFactory<>(QuizForm.class);
    private FormBeanFactory<QuestionForm> formBeanFactoryQuestion = new FormBeanFactory<>(QuestionForm.class);

    private QuizDAO quizDAO;
    private QuestionDAO questionDAO;
    private UserDAO userDAO;

    public CreateQuizAction(Model model) {
        quizDAO = model.getQuizDAO();
        questionDAO = model.getQuestionDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "create-quiz.do";
    }

    public String performPost(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        try {
            request.setAttribute("users", userDAO.getItems(u.getUserName()));
            request.setAttribute("user", u);
            
            String[] questions = request.getParameterValues("question");
            String[] answers = request.getParameterValues("answer");

            QuestionForm questionForm = formBeanFactoryQuestion.create(request);
            QuizForm quizForm = formBeanFactoryQuiz.create(request);

            if (quizForm.hasValidationErrors() || questionForm.hasValidationErrors()) {
                request.setAttribute("quizzes", quizDAO.getItems( u.getUserName() ));
                request.setAttribute("quizForm", quizForm);
                return("prof.jsp");
            }
            
            int num = 0;
            for (int i=0; i<questions.length; i++) {
                if (!questions[i].isEmpty() && !answers[i].isEmpty()) {num++;}
            }
            QuizBean bean1 = new QuizBean();
            bean1.setTitle(quizForm.getTitle());
            bean1.setUserName(u.getUserName());
            bean1.setQNumber(num);
            quizDAO.add(bean1);
            
            for (int i=0; i<questions.length; i++) {
                if (!questions[i].isEmpty() && !answers[i].isEmpty()) {
                    QuestionBean bean = new QuestionBean();
                    bean.setQuestion(questions[i]);
                    bean.setAnswer(answers[i]);
                    bean.setQuizId(bean1.getId());
                    questionDAO.add(bean);
                    num++;
                }
            }

            request.removeAttribute("quizzes");
            request.removeAttribute("quizForm");
            return Action.perform("prof.do", request);

        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
    
    public String performGet(HttpServletRequest request) {
        return Action.perform("prof.do", request);
    }
}

