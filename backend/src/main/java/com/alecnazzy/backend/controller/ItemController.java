package com.alecnazzy.backend.controller;

import com.alecnazzy.backend.model.Item;
import com.alecnazzy.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemRepository.findAll());
    }

    @PostMapping("/items/create")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemRepository.save(item));
    }

    @DeleteMapping("/items/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/items/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Item existingItem = itemRepository.findById(id).orElse(null);
        if (existingItem == null) {
            return ResponseEntity.notFound().build();
        }
        existingItem.setName(item.getName());
        existingItem.setQuantity(item.getQuantity());
        existingItem.setExpiration(item.getExpiration());
        return ResponseEntity.ok(itemRepository.save(existingItem));
    }
}