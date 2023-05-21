package studio.dboo.reference.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import studio.dboo.reference.domain.Item;
import studio.dboo.reference.repository.BlockingItemRepository;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner init(BlockingItemRepository repository) {
        return arg -> {
          repository.save(new Item("Alf alarm clock", 19.99));
          repository.save(new Item("Alf alarm clock", 19.99));
        };
    }
}
