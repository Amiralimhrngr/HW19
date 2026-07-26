package ir.maktabsharif.repository;

import ir.maktabsharif.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T extends BaseModel<ID>, ID extends Number> {
    void save(T t);
    Optional<T> findById(ID id);
    void update(T t);
    void delete(T t);
    List<T> findAll();
}
