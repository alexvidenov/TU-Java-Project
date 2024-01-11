package com.example.server.repositoriesTests;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShopEntity;
import com.example.server.persistence.repositories.ItemRepository;
import com.example.server.persistence.repositories.ShopRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShopRepositoryTest {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveShop_shouldReturnSavedShop() {
        ShopEntity testShop = new ShopEntity();
        ShopEntity savedShop = shopRepository.save(testShop);

        assertEquals(entityManager.find(ShopEntity.class, savedShop.id).id, testShop.id);
        assertEquals(entityManager.find(ShopEntity.class, savedShop.id).getName(), testShop.getName());
        // Add more assertions as needed
    }

    @Test
    void getShopById_shouldReturnShopIfExists() {
        ShopEntity testShop = new ShopEntity();
        shopRepository.save(testShop);

        ShopEntity resultShop = shopRepository.getShopById(testShop.id);

        assertEquals(entityManager.find(ShopEntity.class, resultShop.id).id, testShop.id);
        assertEquals(entityManager.find(ShopEntity.class, resultShop.id).getName(), testShop.getName());
        // Add more assertions as needed
    }

    @Test
    void getAllShops_shouldReturnListOfShops() {
        ShopEntity testShop1 = new ShopEntity();
        ShopEntity testShop2 = new ShopEntity();
        ShopEntity testShop3 = new ShopEntity();

        List<ShopEntity> testShops = Arrays.asList(testShop1, testShop2, testShop3);

        shopRepository.saveAll(testShops);

        List<ShopEntity> resultList = shopRepository.getAllShops();

        assertEquals(testShops.size(), resultList.size());
    }

    @Test
    void deleteShop_shouldReturnTrueIfDeleted() {
        ShopEntity testShop = new ShopEntity();
        shopRepository.save(testShop);

        var result = shopRepository.deleteShop(testShop.id);

        assertEquals(1, result);
    }

    @Test
    void getShopByNameContaining_shouldReturnListOfShops() {
        ShopEntity testShop1 = new ShopEntity();
        testShop1.setName("shopname1");
        ShopEntity testShop2 = new ShopEntity();
        testShop2.setName("shopname2");
        ShopEntity testShop3 = new ShopEntity();
        testShop3.setName("shopname3");

        List<ShopEntity> testShops = Arrays.asList(testShop1, testShop2, testShop3);

        shopRepository.saveAll(testShops);

        List<ShopEntity> resultList = shopRepository.getShopsByNameContaining("shopname");

        assertEquals(testShops.size(), resultList.size());
    }

    @Test
    void getShopByDescriptionContaining_shouldReturnListOfShops() {
        ShopEntity testShop1 = new ShopEntity();
        testShop1.setName("description1");
        ShopEntity testShop2 = new ShopEntity();
        testShop2.setName("description2");
        ShopEntity testShop3 = new ShopEntity();
        testShop3.setName("description3");

        List<ShopEntity> testShops = Arrays.asList(testShop1, testShop2, testShop3);

        shopRepository.saveAll(testShops);

        List<ShopEntity> resultList = shopRepository.getShopsByNameContaining("description");

        assertEquals(testShops.size(), resultList.size());
    }

    @Test
    void getShopByItemId_ShouldReturnShopIfExists(){
        ShopEntity testShop = new ShopEntity();
        shopRepository.save(testShop);

        ItemEntity testItem = new ItemEntity();
        testItem.setShop(testShop);
        itemRepository.save(testItem);

        ShopEntity resultShop = shopRepository.getShopByItemId(testItem.id);

        assertNotNull(resultShop);
        assertEquals(testShop.id, resultShop.id);
    }
}
