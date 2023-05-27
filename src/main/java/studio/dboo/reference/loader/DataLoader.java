package studio.dboo.reference.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import studio.dboo.reference.domain.Item;

import java.util.List;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner init(MongoOperations mongo) {
        return arg -> {
            List<Item> all = mongo.findAll(Item.class);
            System.out.println("DATA LOADER START");
            if(all.size() < 1){
                mongo.save(new Item("알람 시계", 19.99));
                mongo.save(new Item("스머프 티비 선반", 24.99));
            }
        };
    }
}
