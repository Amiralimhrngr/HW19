package ir.maktabsharif.service;

import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.model.BaseModel;

public interface BaseService<T extends BaseModel<ID>, ID extends Number> {
    void save(T t);

    void update(T t);

    T findById(ID id) throws BusinessException;

    void delete(T t);

    void validation(T t);
}
