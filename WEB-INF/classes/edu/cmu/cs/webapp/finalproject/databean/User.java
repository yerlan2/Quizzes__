package edu.cmu.cs.webapp.finalproject.databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("userName")
public class User {
    private String userName;
    private String password;

    public String getUserName()        { return userName; }
    public String getPassword()        { return password; }

    @MaxSize(127)
    public void setUserName(String s)  { userName = s;    }
    public void setPassword(String s)  { password = s;    }
}
