package edu.cmu.cs.webapp.finalproject.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.finalproject.databean.QuizBean;

public class QuizDAO extends GenericDAO<QuizBean> {
    public QuizDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(QuizBean.class, tableName, cp);
	}

	public void add(QuizBean item) throws RollbackException {
		try {
			Transaction.begin();
            create(item);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public QuizBean[] getItems(String userName) throws RollbackException {
		QuizBean[] items = match(MatchArg.equals("userName", userName));
		return items;
    }
    
	public QuizBean getItem(int id) throws RollbackException {
		QuizBean[] item = match(MatchArg.equals("id", id));
		return item[0];
    }
    
    public void delete1(int id) throws RollbackException {
        if (!Transaction.isActive()) {
            try {
                Transaction.begin();
                delete1(id);
                Transaction.commit();
                return;
            } finally {
                if (Transaction.isActive()) {
                    Transaction.rollback();
                }
            }
        }
        QuizBean[] a = match(MatchArg.equals("id", id));
        if (a.length > 0) {
            for (int i=0; i<a.length; i++) {
                delete(a[i].getId());
            }
        }
    }
}
