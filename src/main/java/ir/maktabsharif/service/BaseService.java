package ir.maktabsharif.service;

import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.model.BaseModel;

import java.util.List;

public interface BaseService <T extends BaseModel<ID>,
        ID extends Number,
        E extends BusinessException> {
    void save(T t);

    void update(T t);

    T findById(ID id) throws E;

    void delete(T t);

    List<T> findAll();

    void validation(T t);
}
