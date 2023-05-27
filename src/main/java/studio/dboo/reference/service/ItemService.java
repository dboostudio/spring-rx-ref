package studio.dboo.reference.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import studio.dboo.reference.domain.Item;
import studio.dboo.reference.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    Flux<Item> search(String partialName, String partialDescription, boolean useAnd) {
        if (partialName != null) {
            if (partialDescription != null) {
                if (useAnd) {
                    return repository //
                            .findByNameContainingAndDescriptionContainingAllIgnoreCase( //
                                    partialName, partialDescription);
                } else {
                    return repository.findByNameContainingOrDescriptionContainingAllIgnoreCase( //
                            partialName, partialDescription);
                }
            } else {
                return repository.findByNameContaining(partialName);
            }
        } else {
            if (partialDescription != null) {
                return repository.findByDescriptionContainingIgnoreCase(partialDescription);
            } else {
                return repository.findAll();
            }
        }
    }

    Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);
        ExampleMatcher matcher = (useAnd ? ExampleMatcher.matchingAll() : ExampleMatcher.matchingAny())
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");

        Example<Item> probe = Example.of(item, matcher);

        return repository.findAll(probe);
    }

}
