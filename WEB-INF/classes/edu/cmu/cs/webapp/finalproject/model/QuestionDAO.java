package edu.cmu.cs.webapp.finalproject.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.finalproject.databean.QuestionBean;

public class QuestionDAO extends GenericDAO<QuestionBean> {
    public QuestionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(QuestionBean.class, tableName, cp);
    }
    
    public void add(QuestionBean item) throws RollbackException {
		try {
			Transaction.begin();
			create(item);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
    }
    
    public QuestionBean[] getItems(int quizId) throws RollbackException {
		QuestionBean[] questions = match(MatchArg.equals("quizId", quizId));
		return questions;
    }
    
    public void delete1(int quizId) throws RollbackException {
        if (!Transaction.isActive()) {
            try {
                Transaction.begin();
                delete1(quizId);
                Transaction.commit();
                return;
            } finally {
                if (Transaction.isActive()) {
                    Transaction.rollback();
                }
            }
        }
        QuestionBean[] a = match(MatchArg.equals("quizId", quizId));
        if (a.length > 0) {
            for (int i=0; i<a.length; i++) {
                delete(a[i].getId());
            }
        }
    }
}
