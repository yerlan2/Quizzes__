package edu.cmu.cs.webapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.finalproject.databean.User;
import edu.cmu.cs.webapp.finalproject.formbean.LoginForm;
import edu.cmu.cs.webapp.finalproject.model.Model;
import edu.cmu.cs.webapp.finalproject.model.UserDAO;

public class LoginAction extends Action {
    private FormBeanFactory<LoginForm> formBeanFactory = new FormBeanFactory<>(LoginForm.class);
    
    private UserDAO userDAO;

    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "login.do";
    }
    
    public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "prof.do";
        }

        // Otherwise, just display the login page.
        request.setAttribute("form", new LoginForm());
        return "login.jsp";
    }
        
    public String performPost(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "prof.do";
        }

        try {
            LoginForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "login.jsp";
            }

            if (form.getAction().equals("Register")) {
                User newUser = new User();
                newUser.setUserName(form.getUserName());
                newUser.setPassword(form.getPassword());
                try {
                    userDAO.create(newUser);
                    session.setAttribute("user", newUser);
                    return ("prof.do");
                } catch (DuplicateKeyException e) {
                    form.addFieldError("userName",
                            "A user with this name already exists");
                    return "login.jsp";
                }
            }

            // Look up the user
            User user = userDAO.read(form.getUserName());

            if (user == null) {
                form.addFieldError("userName", "User Name not found");
                return "login.jsp";
            }

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "login.jsp";
            }

            // Attach (this copy of) the user bean to the session
            session.setAttribute("user", user);

            // Redirect to the "todolist" action
            return "prof.do";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
