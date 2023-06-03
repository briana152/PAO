package persistente;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {

    T save(T entity);

    List<T> findAll();

    Optional<T> findById(String... values);

    void update(T entity, String... newValues);

    void delete(T entity);

}