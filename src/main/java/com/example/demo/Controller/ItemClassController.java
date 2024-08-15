package com.example.demo.Controller;

import com.example.demo.DTO.DeleteOrDeactivateDTO;
import com.example.demo.DTO.ItemClassDTO;
import com.example.demo.Entity.ItemClass;
import com.example.demo.Repository.ItemClassRepository;
import com.example.demo.Service.ItemClassService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemClassController {

    @Autowired
    public ItemClassController(ItemClassService itemClassService, ItemClassRepository itemClassRepository){
        this.itemClassService = itemClassService;
        this.itemClassRepository = itemClassRepository;
    }

    @Autowired
    private ItemClassRepository itemClassRepository;

    @Autowired
    private ItemClassService itemClassService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ItemClassDTO itemClassDTO){
        itemClassService.createItemClass(itemClassDTO);
        return ResponseEntity.ok("Item created");
    }

    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody ItemClassDTO itemClassDTO){
        itemClassService.editItemClass(itemClassDTO.getName(), itemClassDTO);
        return ResponseEntity.ok("Item edited");
    }

    @PostMapping("/get_all")
    public List<ItemClass> getAllItemClasses() {
        return itemClassRepository.findByActiveFlagTrue();
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivate(@RequestBody DeleteOrDeactivateDTO deleteOrActivateDTO){
        return itemClassRepository.findByName(deleteOrActivateDTO.getName())
                .map(itemClass -> {itemClass.setActiveFlag(false);
        itemClassRepository.save(itemClass);
        return ResponseEntity.ok("Item: "+ deleteOrActivateDTO.getName() + " Deactivated");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item was not found"));
    }

    @PostMapping("/delete")
    @Transactional
    public ResponseEntity<String> delete(@RequestBody DeleteOrDeactivateDTO deleteOrDeactivateDTO){
        return itemClassRepository.findByName(deleteOrDeactivateDTO.getName())
                .map(itemClass -> {itemClassRepository.deleteByName(deleteOrDeactivateDTO.getName());
            return ResponseEntity.ok("Item: "+ deleteOrDeactivateDTO.getName() + " Deleted");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item was not found"));
    }


}
