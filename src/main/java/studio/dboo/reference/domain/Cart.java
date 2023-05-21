package studio.dboo.reference.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    private String id;
    private List<CartItem> cartItemList;

    public Cart(String id) {
        this.id = id;
        this.cartItemList = new ArrayList<>();
    }
}
