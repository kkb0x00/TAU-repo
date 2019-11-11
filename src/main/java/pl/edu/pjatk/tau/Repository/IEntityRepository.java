package pl.edu.pjatk.tau.Repository;

import pl.edu.pjatk.tau.Exceptions.InvalidIdException;

import java.util.List;

public interface IEntityRepository<TEntity> {

    TEntity get(int id) throws InvalidIdException;
    List<TEntity> getAll();
    void add(TEntity element) throws InvalidIdException;
    void update(TEntity element) throws InvalidIdException;
    void delete(int id) throws InvalidIdException;
}
