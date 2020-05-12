package repo;

import entity.Forum;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.Transactional;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ForumRepository extends AbstractRepo<Forum> {
    @Override
    protected Class<Forum> getType() {
        return Forum.class;
    }

    @Transactional
    public Forum updateForum(Forum forum) {
        Session session = (Session) entityManager.getDelegate();
        session.saveOrUpdate(forum);
        session.flush();
        return forum;
    }
}
