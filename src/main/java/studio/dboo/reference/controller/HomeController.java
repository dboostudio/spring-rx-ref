package studio.dboo.reference.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import studio.dboo.reference.domain.Cart;
import studio.dboo.reference.domain.CartItem;
import studio.dboo.reference.domain.Item;
import studio.dboo.reference.repository.CartRepository;
import studio.dboo.reference.repository.ItemRepository;
import studio.dboo.reference.service.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    private final CartService cartService;

    @GetMapping("/item")
    public Flux<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping("cart")
    public Mono<Cart> getCarts() {
        String myCartId = "My Cart";
        return cartRepository.findById(myCartId)
                .defaultIfEmpty(new Cart(myCartId));
    }

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        String myCartId = "My Cart";
        return cartService.addToCart(myCartId, id).thenReturn("ok");
    }
}