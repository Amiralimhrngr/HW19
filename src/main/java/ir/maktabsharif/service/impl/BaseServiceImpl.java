package ir.maktabsharif.service.impl;

import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.model.BaseModel;
import ir.maktabsharif.repository.GenericRepository;
import ir.maktabsharif.service.BaseService;

import java.util.function.Supplier;

public abstract class BaseServiceImpl<T extends BaseModel<ID>, ID extends Number, R extends GenericRepository<T, ID> , E extends BusinessException> implements BaseService<T, ID> {
    private final R repository;
    private final Supplier<E> exceptionSupplier;

    public BaseServiceImpl(R repository, Supplier<E> exceptionSupplier) {
        this.repository = repository;
        this.exceptionSupplier = exceptionSupplier;
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
    public T findById(ID id) throws E {
        return repository.findById(id).orElseThrow(exceptionSupplier);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }
}
