package repo;

import entity.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserRepository extends AbstractRepo<User> {

    @Override
    protected Class<User> getType() {
        return User.class;
    }

    public User getUserByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(cb.equal(root.get("name"),name));
        return entityManager.createQuery(query).getSingleResult();
    }


}
