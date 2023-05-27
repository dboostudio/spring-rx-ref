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

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @GetMapping("/item")
    public Flux<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping("cart")
    public Mono<Cart> getCarts() {
        return cartRepository.findById("My Cart")
                .defaultIfEmpty(new Cart("My Cart"));
    }

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return
                this.cartRepository.findById("My Cart")
                .defaultIfEmpty(new Cart("My Cart"))
                .flatMap(cart -> cart.getCartItems().stream()
                        .filter(cartItem -> cartItem.getItem()
                                .getId().equals(id))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElseGet(() -> {
                            return this.itemRepository.findById(id)
                                    .map(item -> new CartItem(item))
                                    .map(cartItem -> {
                                        cart.getCartItems().add(cartItem);
                                        return cart;
                                    });
                        }))
                .flatMap(cart -> this.cartRepository.save(cart))
                .thenReturn("ok");
    }
}