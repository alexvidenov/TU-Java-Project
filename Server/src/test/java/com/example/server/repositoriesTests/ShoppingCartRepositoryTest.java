package com.example.server.repositoriesTests;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShoppingCartEntity;
import com.example.server.persistence.entities.UserEntity;
import com.example.server.persistence.repositories.ItemRepository;
import com.example.server.persistence.repositories.ShoppingCartRepository;
import com.example.server.persistence.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShoppingCartRepositoryTest {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveShoppingCart_shouldReturnSaveItem(){
        ShoppingCartEntity testShoppingCart =  new ShoppingCartEntity();
        ShoppingCartEntity savedShoppingCart = shoppingCartRepository.save(testShoppingCart);

        assertEquals(entityManager.find(ShoppingCartEntity.class, savedShoppingCart.id).id, testShoppingCart.id);
    }

    @Test
    void getShoppingCartById_shouldReturnShoppingCartIfExists(){
        ShoppingCartEntity testShoppingCart = new ShoppingCartEntity();
        shoppingCartRepository.save(testShoppingCart);

        ShoppingCartEntity resultShoppingCart = shoppingCartRepository.getShoppingCartById(testShoppingCart.id);

        assertEquals(entityManager.find(ShoppingCartEntity.class, resultShoppingCart.id).id, testShoppingCart.id);
    }

    @Test
    void getAllShoppingCarts_shouldReturnListOfShoppingCarts(){
        ShoppingCartEntity testShoppingCart1 = new ShoppingCartEntity();
        ShoppingCartEntity testShoppingCart2 = new ShoppingCartEntity();
        ShoppingCartEntity testShoppingCart3 = new ShoppingCartEntity();

        List<ShoppingCartEntity> testShoppingCarts = new ArrayList<>();
        testShoppingCarts.add(testShoppingCart1);
        testShoppingCarts.add(testShoppingCart2);
        testShoppingCarts.add(testShoppingCart3);

        shoppingCartRepository.saveAll(testShoppingCarts);

        List<ShoppingCartEntity> resultList = shoppingCartRepository.getAllShoppingCarts();

        assertEquals(testShoppingCarts.get(0).id, resultList.get(0).id);
        assertEquals(testShoppingCarts.get(1).id, resultList.get(1).id);
        assertEquals(testShoppingCarts.get(2).id, resultList.get(2).id);
    }

    @Test
    void deleteShoppingCart_shouldReturnTrueIfDeleted(){
        ShoppingCartEntity originShoppingCart = new ShoppingCartEntity();
        shoppingCartRepository.save(originShoppingCart);

        var result = shoppingCartRepository.deleteShoppingCart(originShoppingCart.id);

        assertEquals(1, result);
    }

    @Test
    void getShoppingCartByUser_shouldReturnShoppingCartIfExists(){
        UserEntity testUser = new UserEntity();
        userRepository.save(testUser);

        ShoppingCartEntity testShoppingCart = new ShoppingCartEntity();
        testShoppingCart.setUser(testUser);
        shoppingCartRepository.save(testShoppingCart);

        ShoppingCartEntity resultShoppingCart = shoppingCartRepository.getShoppingCartByUser(testUser);

        assertNotNull(resultShoppingCart);
        assertEquals(testShoppingCart.id, resultShoppingCart.id);
    }

    @Test
    void getShoppingCartByUserId_shouldReturnShoppingCartIfExists(){
        UserEntity testUser = new UserEntity();
        userRepository.save(testUser);

        ShoppingCartEntity testShoppingCart = new ShoppingCartEntity();
        testShoppingCart.setUser(testUser);
        shoppingCartRepository.save(testShoppingCart);

        ShoppingCartEntity resultShoppingCart = shoppingCartRepository.getShoppingCartByUserId(testUser.id);

        assertNotNull(resultShoppingCart);
        assertEquals(testShoppingCart.id, resultShoppingCart.id);
    }

    @Test
    void getItemsFromShoppingCart_shouldReturnListOfItems(){
        ItemEntity testItem1 = new ItemEntity();
        ItemEntity testItem2 = new ItemEntity();
        ItemEntity testItem3 = new ItemEntity();

        List<ItemEntity> testItems = new ArrayList<>();
        testItems.add(testItem1);
        testItems.add(testItem2);
        testItems.add(testItem3);

        itemRepository.saveAll(testItems);

        ShoppingCartEntity testShoppingCart = new ShoppingCartEntity();
        testShoppingCart.addItem(testItem1);
        testShoppingCart.addItem(testItem2);
        testShoppingCart.addItem(testItem3);
        shoppingCartRepository.save(testShoppingCart);

        List<ItemEntity> resultItems = shoppingCartRepository.getItemsFromShoppingCart(testShoppingCart);

        assertEquals(testItems.size(), resultItems.size());
    }

    @Test
    void getItemsFromUsersShoppingCart_shouldReturnListOfItems(){
        ItemEntity testItem1 = new ItemEntity();
        ItemEntity testItem2 = new ItemEntity();
        ItemEntity testItem3 = new ItemEntity();

        List<ItemEntity> testItems = new ArrayList<>();
        testItems.add(testItem1);
        testItems.add(testItem2);
        testItems.add(testItem3);

        itemRepository.saveAll(testItems);

        ShoppingCartEntity testShoppingCart = new ShoppingCartEntity();
        testShoppingCart.addItem(testItem1);
        testShoppingCart.addItem(testItem2);
        testShoppingCart.addItem(testItem3);

        UserEntity testUser = new UserEntity();
        testUser.setShoppingCart(testShoppingCart);
        userRepository.save(testUser);
        shoppingCartRepository.save(testShoppingCart);

        List<ItemEntity> resultItems = shoppingCartRepository.getItemsFromUsersShoppingCart(testUser);

        System.out.println("result: " + resultItems.toString());

        assertEquals(testItems.size(), resultItems.size());
    }

    @Test
    void getItemsFromUsersShoppingCartByUserId_shouldReturnListOfItems(){
        ItemEntity testItem1 = new ItemEntity();
        ItemEntity testItem2 = new ItemEntity();
        ItemEntity testItem3 = new ItemEntity();

        List<ItemEntity> testItems = new ArrayList<>();
        testItems.add(testItem1);
        testItems.add(testItem2);
        testItems.add(testItem3);

        itemRepository.saveAll(testItems);

        ShoppingCartEntity testShoppingCart = new ShoppingCartEntity();
        testShoppingCart.addItem(testItem1);
        testShoppingCart.addItem(testItem2);
        testShoppingCart.addItem(testItem3);

        UserEntity testUser = new UserEntity();
        testUser.setShoppingCart(testShoppingCart);
        userRepository.save(testUser);
        shoppingCartRepository.save(testShoppingCart);

        List<ItemEntity> resultItems = shoppingCartRepository.getItemsFromUsersShoppingCartByUserId(testUser.id);

        assertEquals(testItems.size(), resultItems.size());
    }
}
