package edu.cmu.cs.webapp.finalproject.databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class QuizBean {
    private int     id;
    private int     qNumber;
    private String  title;
	private String  userName;
	
	public  int     getId()         { return id; }
	public  int     getQNumber()    { return qNumber; }
    public  String  getTitle()      { return title; }
    public  String  getUserName()   { return userName; }

    public void setId(int i)            { id = i; }
    public void setQNumber(int i)       { qNumber = i; }
	@MaxSize(255)
	public void setTitle(String s)      { title = s; }
	@MaxSize(127)
	public void setUserName(String s)   { userName = s; }
}
