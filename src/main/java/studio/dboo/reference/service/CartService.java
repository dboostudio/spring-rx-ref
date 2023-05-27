package studio.dboo.reference.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import studio.dboo.reference.domain.Cart;
import studio.dboo.reference.domain.CartItem;
import studio.dboo.reference.repository.CartRepository;
import studio.dboo.reference.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public Mono<Cart> addToCart(String cartId, String id) {
        return this.cartRepository.findById("My Cart")
                .defaultIfEmpty(new Cart("My Cart"))
                .flatMap(
                        cart -> cart.getCartItems().stream()
                                .filter(cartItem -> cartItem.getItem().getId().equals(id))
                                .findAny()
                                .map(cartItem -> {
                                    cartItem.increment();
                                    return Mono.just(cart);
                                })
                                .orElseGet(() -> {
                                    return this.itemRepository.findById(id)
                                            .map(CartItem::new)
                                            .map(cartItem -> {
                                                cart.getCartItems().add(cartItem);
                                                return cart;
                                            });
                                }))
                .flatMap(this.cartRepository::save);
    }
}
