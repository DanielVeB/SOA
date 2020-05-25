package repo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.*;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.UUID;

public abstract class AbstractRepo<T> {

    @PersistenceContext(unitName = "SOA")
    protected EntityManager entityManager;

    @Resource
    protected UserTransaction transaction;

    protected abstract Class<T> getType();

    public T create(T entity) throws RollbackException {
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

    public T update(T entity) {
        try {
            transaction.begin();
            T merged = entityManager.merge(entity);
            transaction.commit();
            return merged;
        } catch (ConstraintViolationException | NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException ex) {
            return null;
        } catch (RollbackException ex) {
            System.out.println("ROLLBACK. Optimistic loop exception");
            return null;
        }
    }

    public void delete(T entity) {
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            transaction.commit();
        } catch (NotSupportedException | HeuristicMixedException | SystemException | HeuristicRollbackException | RollbackException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<T> read() {
        return entityManager.createQuery("FROM " + getType().getName(), getType()).getResultList();
    }

    public T getById(UUID id){
        return entityManager.find(getType(), id);
    }

    @Transactional
    public void deleteById(UUID id) throws IllegalArgumentException{
        entityManager.remove(entityManager.find(getType(), id));
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

}
