package pl.edu.pjatk.tau.Repository;

import java.util.List;

public interface IEntityRepository<TEntity> {

    TEntity get(int id);
    List<TEntity> getAll();
    void add(TEntity element);
    void update(TEntity element);
    void delete(int id);
}
