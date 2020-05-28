package repo;

import org.hibernate.Session;
import org.hibernate.query.Query;
import repo.entity.Movie;
import repo.entity.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserRepo extends AbstractRepo<User> {

    @Override
    protected Class<User> getType() {
        return User.class;
    }

    @Transactional
    public List<User> getUsers(int offset, int limit){
        Session session = (Session) entityManager.getDelegate();
        Query<User> query = session.createQuery("From User");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public List<Movie> getUserMovies(UUID userID){
        return getById(userID).getMovies();
    }

}
