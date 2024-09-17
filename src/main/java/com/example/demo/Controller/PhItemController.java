package com.example.demo.Controller;

import com.example.demo.DTO.*;
import com.example.demo.Entity.ItemVariantDimension;
import com.example.demo.Service.PhItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class PhItemController {

    @Autowired
    private PhItemService phItemService;

    @DeleteMapping("/unselectVariant")
    public ResponseEntity<String> unselectVariant(@RequestBody ItemSidRequest itemSidRequest) {
        phItemService.unselectVariant(itemSidRequest.getItemSid());
        return ResponseEntity.ok("Variants removed");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createFullItem(@RequestBody FullItemDto fullItemDto) {
        try {
            phItemService.saveFullItem(fullItemDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Item created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating item: " + e.getMessage());
        }
    }

}