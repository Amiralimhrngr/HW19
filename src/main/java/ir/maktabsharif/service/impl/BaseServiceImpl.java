package ir.maktabsharif.service.impl;

import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.model.BaseModel;
import ir.maktabsharif.repository.GenericRepository;
import ir.maktabsharif.service.BaseService;

public abstract class BaseServiceImpl<T extends BaseModel<ID>, ID, R extends GenericRepository<T, ID>> implements BaseService<T, ID> {
    R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(T t) {
        validation(t);
        repository.save(t);
    }

    @Override
    public void update(T t) {
        validation(t);
        repository.update(t);
    }

    @Override
    public T findById(ID id) throws BusinessException {
        return repository.findById(id).orElseThrow(() -> new BusinessException("No entity with Id: " + id));
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }
}
