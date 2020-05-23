package repo;

import repo.entity.Movie;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class MovieRepo extends AbstractRepo<Movie> {
    @Override
    protected Class<Movie> getType() {
        return Movie.class;
    }
}
