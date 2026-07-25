package ir.maktabsharif.repository.impl;

import ir.maktabsharif.exception.DatabaseOperationException;
import ir.maktabsharif.model.BaseModel;
import ir.maktabsharif.repository.GenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Optional;

public abstract class GenericRepositoryImpl<T extends BaseModel<ID>, ID extends Number> implements GenericRepository<T, ID> {
    EntityManagerFactory emf;

    public GenericRepositoryImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(T t) {
        EntityManager em = emf.createEntityManager();
        try (em) {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DatabaseOperationException("Save failed! " + e.getMessage());
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        EntityManager em = emf.createEntityManager();
        try (em) {
            em.getTransaction().begin();
            Optional<T> object = Optional.ofNullable(em.find(getEntityClass(), id));
            em.getTransaction().commit();
            return object;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DatabaseOperationException("Find by Id failed! " + e.getMessage());
        }
    }

    @Override
    public void update(T t) {
        EntityManager em = emf.createEntityManager();
        try (em) {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DatabaseOperationException("Update failed! " + e.getMessage());
        }
    }

    @Override
    public void delete(T t) {
        EntityManager em = emf.createEntityManager();
        try (em) {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DatabaseOperationException("Delete failed!");
        }
    }

    public abstract Class<T> getEntityClass();
}
