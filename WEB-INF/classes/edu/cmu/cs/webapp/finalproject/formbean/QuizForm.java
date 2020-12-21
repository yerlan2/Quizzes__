package edu.cmu.cs.webapp.finalproject.formbean;

import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;

public class QuizForm extends FormBean {
    private String action;
    private String title;

    public String getAction() {
        return action;
    }
    public String getTitle() {
        return title;
    }

    @InputType("button")
    public void setAction(String action) {
        this.action = action;
    }
    @Label("Quiz Title:")
    public void setTitle(String title) {
        this.title = title;
    }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        if (!action.equals("create-quiz")) {
            addFormError("Invalid action: " + action);
        }
    }
}
