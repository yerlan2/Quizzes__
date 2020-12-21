package edu.cmu.cs.webapp.finalproject.formbean;

import org.formbeanfactory.FormBean;
import org.formbeanfactory.Label;

public class QuestionForm extends FormBean {
    private String question;
    private String answer;

    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }

    @Label("Answer")
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    @Label("Question")
    public void setQuestion(String question) {
        this.question = question;
    }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
    }
}
