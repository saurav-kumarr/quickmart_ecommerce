package com.quickmart.service;

import com.quickmart.exceptions.APIException;
import com.quickmart.exceptions.ResourceNotFoundException;
import com.quickmart.model.Cart;
import com.quickmart.model.CartItem;
import com.quickmart.model.Product;
import com.quickmart.payload.CartDTO;
import com.quickmart.payload.ProductDTO;
import com.quickmart.repositories.CartItemRepository;
import com.quickmart.repositories.CartRepository;
import com.quickmart.repositories.ProductRepository;
import com.quickmart.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthUtil authUtil;

    @Override
    public CartDTO addProductToCart(Long productId, Integer quantity) {

        // Find existing cart or create one

        Cart cart = createCart();

        // Retrieve Product Details
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        // Perform Validations
        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(
                cart.getCartId(),
                productId
        );

        if(cartItem != null){
            throw new APIException("Product " + product.getProductName() + "already exists in the cart");
        }

        if(product.getQuantity() == 0){
            throw new APIException(product.getProductName() + " is not available");
        }

        if(product.getQuantity() < quantity){
            throw new APIException("Please, make an order of the " + product.getProductName()
            + " less than or equal to the quantity " + product.getQuantity() + ".");
        }

        // Create cart Item
        CartItem newCartItem = new CartItem();

        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(quantity);
        newCartItem.setDiscount(product.getDiscount());
        newCartItem.setProductPrice(product.getSpecialPrice());

        // Save in repository
        cartItemRepository.save(newCartItem);

        product.setQuantity(product.getQuantity());

        cart.setTotalPrice(cart.getTotalPrice() + (product.getSpecialPrice()*quantity));

        cartRepository.save(cart);

        // Return updated cart
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        List<CartItem> cartItems = cart.getCartItems();

        Stream<ProductDTO> productStream = cartItems.stream().map(item -> {
            ProductDTO map = modelMapper.map(item.getProduct(), ProductDTO.class);
            map.setQuantity(item.getQuantity());
            return map;
        });

        cartDTO.setProducts(productStream.toList());

        return cartDTO;
    }



    @Override
    public List<CartDTO> getAllCarts() {

        List<Cart> carts = cartRepository.findAll();

        if(carts.size() == 0){
            throw new APIException("No cart exists!!");
        }

        List<CartDTO> cartDTOs = carts.stream()
                .map(cart -> {
                    CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

                    List<ProductDTO> products = cart.getCartItems().stream()
                            .map(p -> modelMapper.map(p.getProduct(),ProductDTO.class))
                            .collect(Collectors.toList());
                    cartDTO.setProducts(products);

                    return cartDTO;
        }).collect(Collectors.toList());

        return cartDTOs;
    }


    @Override
    public CartDTO getCart(String emailId, Long cartId) {
        Cart cart = cartRepository.findCartByEmailAndCartId(emailId,cartId);

        if(cart == null){
            throw new ResourceNotFoundException("Cart","cartId",cartId);
        }
        CartDTO cartDTO = modelMapper.map(cart,CartDTO.class);

        cart.getCartItems().forEach(c -> c.getProduct().setQuantity(c.getQuantity()));

        List<ProductDTO> products = cart.getCartItems().stream()
                .map(p -> modelMapper.map(p.getProduct(),ProductDTO.class))
                .toList();
        cartDTO.setProducts(products);

        return cartDTO;
    }


    private Cart createCart(){
        Cart userCart = cartRepository.findCartByEmail(authUtil.loggedInEmail());
        if(userCart != null){
            return userCart;
        }

        Cart cart = new Cart();
        cart.setTotalPrice(0.00);
        cart.setUser(authUtil.loggedInUser());
        Cart newCart = cartRepository.save(cart);
        return newCart;
    }
}
