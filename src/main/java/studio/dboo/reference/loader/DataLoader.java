package studio.dboo.reference.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import studio.dboo.reference.domain.Item;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner init(MongoOperations mongo) {
        return arg -> {
          mongo.save(new Item("Alf alarm clock", 19.99));
          mongo.save(new Item("Alf alarm clock", 19.99));
        };
    }
}
