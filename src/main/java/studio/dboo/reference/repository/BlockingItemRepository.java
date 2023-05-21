package studio.dboo.reference.repository;

import org.springframework.data.repository.CrudRepository;
import studio.dboo.reference.domain.Item;

public interface BlockingItemRepository extends CrudRepository<Item, String> {
}
