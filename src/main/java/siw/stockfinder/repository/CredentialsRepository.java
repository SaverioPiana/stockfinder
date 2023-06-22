package siw.stockfinder.repository;

import org.springframework.data.repository.CrudRepository;
import siw.stockfinder.model.Credentials;
import siw.stockfinder.model.Stock;

public interface CredentialsRepository extends CrudRepository<Long, Credentials> {
}
