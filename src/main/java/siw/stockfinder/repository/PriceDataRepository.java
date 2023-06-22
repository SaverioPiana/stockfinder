package siw.stockfinder.repository;

import org.springframework.data.repository.CrudRepository;
import siw.stockfinder.model.PriceData;
import siw.stockfinder.model.Stock;

public interface PriceDataRepository extends CrudRepository<Long, PriceData> {
}
