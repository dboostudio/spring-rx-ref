package studio.dboo.reference.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import studio.dboo.reference.domain.Item;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {

}
