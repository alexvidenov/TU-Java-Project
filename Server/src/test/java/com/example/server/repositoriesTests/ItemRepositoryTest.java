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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveItem_shouldReturnSaveItem(){
        ItemEntity testItem =  new ItemEntity();
        ItemEntity savedItem = itemRepository.save(testItem);

        assertEquals(entityManager.find(ItemEntity.class, savedItem.id).id, testItem.id);
        assertEquals(entityManager.find(ItemEntity.class, savedItem.id).getName(), testItem.getName());
        assertEquals(entityManager.find(ItemEntity.class, savedItem.id).getDescription(), testItem.getDescription());
        assertEquals(entityManager.find(ItemEntity.class, savedItem.id).getShop(), testItem.getShop());
    }

    @Test
    void getItemById_shouldReturnItemIfExists(){
        ItemEntity testItem = new ItemEntity();
        itemRepository.save(testItem);

        ItemEntity resultItem = itemRepository.getItemById(testItem.id);

        assertEquals(entityManager.find(ItemEntity.class, resultItem.id).id, testItem.id);
        assertEquals(entityManager.find(ItemEntity.class, resultItem.id).getName(), testItem.getName());
        assertEquals(entityManager.find(ItemEntity.class, resultItem.id).getDescription(), testItem.getDescription());
        assertEquals(entityManager.find(ItemEntity.class, resultItem.id).getShop(), testItem.getShop());
    }

    @Test
    void getAllItems_shouldReturnListOfItems(){
        ItemEntity testItem1 = new ItemEntity();
        ItemEntity testItem2 = new ItemEntity();
        ItemEntity testItem3 = new ItemEntity();

        List<ItemEntity> testItems = new ArrayList<>();
        testItems.add(testItem1);
        testItems.add(testItem2);
        testItems.add(testItem3);

        itemRepository.saveAll(testItems);

        List<ItemEntity> resultList = itemRepository.getAllItems();

        assertEquals(testItems.get(0).id, resultList.get(0).id);
        assertEquals(testItems.get(1).id, resultList.get(1).id);
        assertEquals(testItems.get(2).id, resultList.get(2).id);
    }

    @Test
    void deleteItem_ShouldReturnTrueIfDeleted(){
        ItemEntity originItem = new ItemEntity();
        itemRepository.save(originItem);

        var result = itemRepository.deleteItem(originItem.id);

        assertEquals(1, result);
    }

    @Test
    void getAllItemsFromShop_shouldReturnListOfItems(){
        ItemEntity testItem1 = new ItemEntity();
        ItemEntity testItem2 = new ItemEntity();
        ItemEntity testItem3 = new ItemEntity();

        List<ItemEntity> testItems = new ArrayList<>();
        testItems.add(testItem1);
        testItems.add(testItem2);
        testItems.add(testItem3);

        ShopEntity testShop = new ShopEntity();
        shopRepository.save(testShop);

        testItems.forEach(itemEntity -> itemEntity.setShop(testShop));

        itemRepository.saveAll(testItems);

        List<ItemEntity> resultList = itemRepository.getAllItemsFromShop(testShop);

        assertEquals(testItems.get(0).id, resultList.get(0).id);
        assertEquals(testItems.get(1).id, resultList.get(1).id);
        assertEquals(testItems.get(2).id, resultList.get(2).id);
    }

    @Test
    void getAllItemsFromShopById_shouldReturnListOfItems(){
        ItemEntity testItem1 = new ItemEntity();
        ItemEntity testItem2 = new ItemEntity();
        ItemEntity testItem3 = new ItemEntity();

        List<ItemEntity> testItems = new ArrayList<>();
        testItems.add(testItem1);
        testItems.add(testItem2);
        testItems.add(testItem3);

        ShopEntity testShop = new ShopEntity();
        shopRepository.save(testShop);

        testItems.forEach(itemEntity -> itemEntity.setShop(testShop));

        itemRepository.saveAll(testItems);

        List<ItemEntity> resultList = itemRepository.getAllItemsFromShopById(testShop.id);

        assertEquals(testItems.get(0).id, resultList.get(0).id);
        assertEquals(testItems.get(1).id, resultList.get(1).id);
        assertEquals(testItems.get(2).id, resultList.get(2).id);
    }

    @Test
    void getItemsWithNameContaining_shouldReturnListOfItems(){
        ItemEntity testItem1 = new ItemEntity();
        testItem1.setName("aaaa");
        ItemEntity testItem2 = new ItemEntity();
        testItem2.setName("aabb");
        ItemEntity testItem3 = new ItemEntity();
        testItem3.setName("bbbb");

        List<ItemEntity> testItems = new ArrayList<>();
        testItems.add(testItem1);
        testItems.add(testItem2);
        testItems.add(testItem3);

        itemRepository.saveAll(testItems);

        List<ItemEntity> resultList = itemRepository.getItemsWithNameContaining("aa");

        assertNotEquals(testItems.size(), resultList.size());
        assertEquals(testItems.get(0).id, resultList.get(0).id);
        assertEquals(testItems.get(1).id, resultList.get(1).id);
    }

    @Test
    void getItemsWithDescriptionContaining_shouldReturnListOfItems(){
        ItemEntity testItem1 = new ItemEntity();
        testItem1.setDescription("aaaa");
        ItemEntity testItem2 = new ItemEntity();
        testItem2.setDescription("aabb");
        ItemEntity testItem3 = new ItemEntity();
        testItem3.setDescription("bbbb");

        List<ItemEntity> testItems = new ArrayList<>();
        testItems.add(testItem1);
        testItems.add(testItem2);
        testItems.add(testItem3);

        itemRepository.saveAll(testItems);

        List<ItemEntity> resultList = itemRepository.getItemsWithDescriptionContaining("aa");

        assertNotEquals(testItems.size(), resultList.size());
        assertEquals(testItems.get(0).id, resultList.get(0).id);
        assertEquals(testItems.get(1).id, resultList.get(1).id);
    }
}
