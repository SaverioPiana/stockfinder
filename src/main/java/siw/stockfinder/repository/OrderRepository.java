package siw.stockfinder.repository;

import org.springframework.data.repository.CrudRepository;
import siw.stockfinder.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
