package edu.cmu.cs.webapp.finalproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.webapp.finalproject.databean.User;
import edu.cmu.cs.webapp.finalproject.model.Model;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
        Model model = new Model(getServletConfig());

        // Action.add(new AddAction(model));
        // Action.add(new DeleteAction(model));
        // Action.add(new DeleteAllAction(model));
        Action.add(new DeleteQuizAction(model));
        Action.add(new CheckQuizAction(model));
        Action.add(new QuizInfoAction(model));
        Action.add(new QuizAction(model));
        Action.add(new VisitorShowAction(model));
        Action.add(new ProfAction(model));
        Action.add(new NewQuizAction(model));
        Action.add(new CreateQuizAction(model));
        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        // Action.add(new ToDoListAction(model));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage, request, response);
    }

    private String performTheAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        User user = (User) session.getAttribute("user");
        String action = getActionName(servletPath);

        if (user != null) {
            return Action.perform(action, request);
        }
        if (action.equals("login.do")) {
            return Action.perform("login.do", request);
        }
        return "controller-stale-session.jsp";
    }

    private void sendToNextPage(String nextPage, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        if (nextPage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    request.getServletPath());
            return;
        }
        if (nextPage.endsWith(".do")) {
            response.sendRedirect(nextPage);
            return;
        }
        if (nextPage.endsWith(".jsp")) {
            RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/" + nextPage);
            d.forward(request, response);
            return;
        }
        throw new ServletException(Controller.class.getName()
                + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

    private String getActionName(String path) {
        int slash = path.lastIndexOf('/');
        return path.substring(slash + 1);
    }
}
