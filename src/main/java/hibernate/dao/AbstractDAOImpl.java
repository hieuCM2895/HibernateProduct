package hibernate.dao;


import java.util.ArrayList;
import java.util.List;

public class AbstractDAOImpl<T> {

    private final Class<T> clazz;

    public AbstractDAOImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> findAll() {
        List<T> object = new ArrayList<>();
        return object;
    }
}
