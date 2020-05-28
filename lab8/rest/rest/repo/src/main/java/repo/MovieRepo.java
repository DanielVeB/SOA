package repo;

import org.hibernate.Session;
import org.hibernate.query.Query;
import repo.entity.Movie;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class MovieRepo extends AbstractRepo<Movie> {

    @Override
    protected Class<Movie> getType() {
        return Movie.class;
    }

    @Transactional
    public List<Movie> getMovies(int offset, int limit,String title) {
        Session session = (Session) entityManager.getDelegate();
        String hql = "FROM Movie";

        if(title != null){
            hql += "WHERE title = :title";
        }
        Query<Movie> query = session
                .createQuery(hql)
                .setParameter("title", title);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public Movie getMovieByTitle(String title) {
        return null;
    }
}
