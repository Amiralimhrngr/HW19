package ir.maktabsharif.service;

import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.model.BaseModel;

public interface BaseService<T extends BaseModel<ID>, ID extends Number, E extends BusinessException> {
    void save(T t);

    void update(T t);

    T findById(ID id) throws E;

    void delete(T t);

    void validation(T t);
}
