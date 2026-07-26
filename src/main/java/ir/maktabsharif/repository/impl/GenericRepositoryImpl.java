package ir.maktabsharif.repository.impl;

import ir.maktabsharif.exception.DatabaseOperationException;
import ir.maktabsharif.model.BaseModel;
import ir.maktabsharif.repository.GenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public abstract class GenericRepositoryImpl<T extends BaseModel<ID>, ID extends Number> implements GenericRepository<T, ID> {
    EntityManagerFactory emf;

    public GenericRepositoryImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(T t) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try (em){
            tx.begin();
            em.persist(t);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()){
                tx.rollback();
            }
            throw new DatabaseOperationException("Save failed! " + e.getMessage());
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try (em) {
            tx.begin();
            Optional<T> object = Optional.ofNullable(em.find(getEntityClass(), id));
            tx.commit();
            return object;
        } catch (Exception e) {
            if (tx.isActive()){
                tx.rollback();
            }
            throw new DatabaseOperationException("Find by Id failed! " + e.getMessage());
        }
    }

    @Override
    public void update(T t) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try (em) {
            tx.begin();
            em.persist(t);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()){
                tx.rollback();
            }
            throw new DatabaseOperationException("Update failed! " + e.getMessage());
        }
    }

    @Override
    public void delete(T t) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try (em) {
            tx.begin();
            em.remove(t);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DatabaseOperationException("Delete failed!");
        }
    }

    @Override
    public List<T> findAll(){
        EntityManager em = emf.createEntityManager();
        try (em) {
            return em.createQuery("SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass()).getResultList();
        } catch (Exception e) {
            throw new DatabaseOperationException("Find all failed!");
        }
    }

    public abstract Class<T> getEntityClass();
}
