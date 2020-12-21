package edu.cmu.cs.webapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.webapp.finalproject.formbean.LoginForm;
import edu.cmu.cs.webapp.finalproject.model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {

    public LogoutAction(Model model) {
    }

    public String getName() {
        return "logout.do";
    }

    public String performGet(HttpServletRequest request) {
        return performPost(request);
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);

        request.setAttribute("form", new LoginForm());
        return "login.jsp";
    }
}
