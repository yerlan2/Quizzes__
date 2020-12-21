package edu.cmu.cs.webapp.finalproject.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.finalproject.databean.QuizInfoBean;

public class QuizInfoDAO extends GenericDAO<QuizInfoBean> {
    public QuizInfoDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(QuizInfoBean.class, tableName, cp);
	}

	public void add(QuizInfoBean item) throws RollbackException {
        delete(item.getUserName(), item.getQuizId());
		try {
			Transaction.begin();
            create(item);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public QuizInfoBean[] getItems(String userName, String creater) throws RollbackException {
        QuizInfoBean[] items = match(MatchArg.equals("userName", userName), MatchArg.equals("creater", creater));
		return items;
    }
	public QuizInfoBean[] getItemsCreater(String creater, int quizId) throws RollbackException {
        QuizInfoBean[] items = match(MatchArg.equals("creater", creater), MatchArg.equals("quizId", quizId));
		return items;
    }
    
	public QuizInfoBean getItem(int id) throws RollbackException {
		QuizInfoBean[] item = match(MatchArg.equals("id", id));
		return item[0];
    }
    
    public void delete(String userName, int quizId) throws RollbackException {
        if (!Transaction.isActive()) {
            try {
                Transaction.begin();
                delete(userName, quizId);
                Transaction.commit();
                return;
            } finally {
                if (Transaction.isActive()) {
                    Transaction.rollback();
                }
            }
        }
        QuizInfoBean[] a = match(MatchArg.equals("userName", userName), MatchArg.equals("quizId", quizId));
        if (a.length > 0) {
            delete(a[0].getId());
        }
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
        QuizInfoBean[] a = match(MatchArg.equals("quizId", quizId));
        if (a.length > 0) {
            for (int i=0; i<a.length; i++) {
                delete(a[i].getId());
            }
        }
    }

    // public void deleteItems(String userName, String visitor) throws RollbackException {
    //     if (!Transaction.isActive()) {
    //         try {
    //             Transaction.begin();
    //             deleteItems(userName, visitor);
    //             Transaction.commit();
    //             return;
    //         } finally {
    //             if (Transaction.isActive()) {
    //                 Transaction.rollback();
    //             }
    //         }
    //     }
        
    //     QuizInfoBean[] a = getItems(userName, visitor);
    //     for (QuizInfoBean b : a) {
    //         delete(b.getUserName(), b.getQuizId());
    //     }
    // }
}
