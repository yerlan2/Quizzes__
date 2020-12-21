package edu.cmu.cs.webapp.finalproject.databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class QuestionBean {
    private int     id;
    private int     quizId;
    private String  question;
    private String  answer;

    public  int     getId()         { return id; }
    public  int     getQuizId()     { return quizId; }
    public  String  getQuestion()   { return question; }
    public  String  getAnswer()     { return answer; }

    public void setId(int i)          { id = i; }
    public void setQuizId(int i)      { quizId = i; }
	@MaxSize(1000)
	public void setQuestion(String s) { question = s; }
	@MaxSize(255)
	public void setAnswer(String s)   { answer = s; }
}
