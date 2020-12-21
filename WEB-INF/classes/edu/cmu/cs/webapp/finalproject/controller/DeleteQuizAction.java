package edu.cmu.cs.webapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.formbean.IdForm;
import edu.cmu.cs.webapp.finalproject.model.Model;
import edu.cmu.cs.webapp.finalproject.model.QuestionDAO;
import edu.cmu.cs.webapp.finalproject.model.QuizDAO;
import edu.cmu.cs.webapp.finalproject.model.QuizInfoDAO;

public class DeleteQuizAction extends Action {
    private FormBeanFactory<IdForm> formBeanFactory = new FormBeanFactory<>(IdForm.class);

    private QuizDAO quizDAO;
    private QuizInfoDAO quizInfoDAO;
    private QuestionDAO questionDAO;

	public DeleteQuizAction(Model model) {
		quizDAO = model.getQuizDAO();
		quizInfoDAO = model.getQuizInfoDAO();
		questionDAO = model.getQuestionDAO();
	}

	public String getName() {
		return "delete-quiz.do";
	}

	public String performPost(HttpServletRequest request) {
        IdForm form = formBeanFactory.create(request);
        if (form.hasValidationErrors()) {
            request.setAttribute("form", form);
            return "error.jsp";
        }

		try {
            request.setAttribute("error12", form.getIdAsInt());
            questionDAO.delete1(form.getIdAsInt());
			quizInfoDAO.delete1(form.getIdAsInt());
			quizDAO.delete1(form.getIdAsInt());
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
