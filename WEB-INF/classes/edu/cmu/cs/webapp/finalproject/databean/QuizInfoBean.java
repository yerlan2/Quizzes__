package edu.cmu.cs.webapp.finalproject.databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class QuizInfoBean {
    private int     id; 
    private int     quizId;
    private int     score;
	private String  userName;
	private String  creater;
	
	public  int     getId()         { return id; }
	public  int     getQuizId()     { return quizId; }
	public  int     getScore()      { return score; }
    public  String  getUserName()   { return userName; }
    public  String  getCreater()    { return creater; }
    
	public void setId(int i)            { id = i; }
    public void setQuizId(int i)        { quizId = i; }
    public void setScore(int i)         { score = i; }
	@MaxSize(127)
	public void setUserName(String s)   { userName = s; }
	@MaxSize(127)
	public void setCreater(String s)    { creater = s; }
}
