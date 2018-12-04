package com.in28minutes.unitesting.unittesting.service;

import com.in28minutes.unitesting.unittesting.data.Item;
import com.in28minutes.unitesting.unittesting.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemBussinessServiceTest {

    @InjectMocks
    ItemBussinessService itemBussinessService;

    @Mock
    ItemRepository itemRepository;

    @Test
    public void retriveAllItems() {
        when(itemRepository.findAll()).thenReturn( Arrays.asList(new Item(1, "Buss", "Timisoara"),
                new Item(2, "Buss", "Timisoara")));
        List<Item> items = itemBussinessService.retriveAllItems();
        assertEquals(100, items.get(0).getValue());
        assertEquals(100, items.get(1).getValue());
    }

}