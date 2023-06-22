package siw.stockfinder.repository;

import org.springframework.data.repository.CrudRepository;
import siw.stockfinder.model.Stock;
import siw.stockfinder.model.User;

public interface UserRepository extends CrudRepository<Long, User> {
}
