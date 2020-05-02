package repo;

import data.Author;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;

import static javax.ejb.TransactionManagementType.BEAN;

@Stateless
@TransactionManagement(BEAN)
public class AuthorRepository extends AbstractRepo<Author> {

    @Override
    protected Class<Author> getType() {
        return Author.class;
    }

}
