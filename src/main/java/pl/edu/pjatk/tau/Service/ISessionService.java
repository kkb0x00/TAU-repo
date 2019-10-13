package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Exceptions.InvalidIdException;

import java.util.List;
import java.util.Map;

interface ISessionService<IElement> {

    IElement get(int id) throws InvalidIdException;
    List<IElement> getAll();
    void add(int id, IElement element) throws InvalidIdException;
    void update(int id, IElement element) throws InvalidIdException;
    void delete(int id) throws InvalidIdException;
}
