package siw.stockfinder.repository;

import org.springframework.data.repository.CrudRepository;
import siw.stockfinder.model.Review;
import siw.stockfinder.model.Stock;

public interface ReviewRepository extends CrudRepository<Long, Review> {
}
