package com.example.demo.Controller;

import com.example.demo.DTO.PhItemClassDto;
import com.example.demo.Service.PhItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class PhItemController {

    @Autowired
    private PhItemService phItemService;

    @PutMapping("/editItem")
    public ResponseEntity<PhItemClassDto> editItem(@RequestBody PhItemClassDto phItemClassDto) throws Exception {
        PhItemClassDto updatedItem = phItemService.editItem(phItemClassDto);
        return ResponseEntity.ok(updatedItem);
    }


}