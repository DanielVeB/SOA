package repo;

import clojure.lang.Cons;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.*;
import javax.validation.ConstraintViolationException;
import java.util.List;

public abstract class AbstractRepo<T> {

    @PersistenceContext(unitName = "SOA")
    protected EntityManager entityManager;

    @Resource
    protected UserTransaction transaction;

    protected abstract Class<T> getType();

    public T create(T entity) throws ConstraintViolationException {
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        }catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

    public void update(T entity) {
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (ConstraintViolationException ex) {
            System.out.println("VALIDATION ERROR");
            System.out.println(ex.getMessage());
        } catch (RollbackException ex){
            System.out.println("ROLLBACK. Optimistic loop exception");
        }
        catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(T entity) {
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            transaction.commit();
        } catch (NotSupportedException | HeuristicMixedException | SystemException | HeuristicRollbackException | javax.transaction.RollbackException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<T> read() {
        return entityManager.createQuery("FROM " + getType().getName(), getType()).getResultList();
    }

    public CriteriaBuilder getCriteriaBuilder(){
        return entityManager.getCriteriaBuilder();
    }

}
