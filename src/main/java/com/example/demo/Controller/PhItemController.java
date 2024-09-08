package com.example.demo.Controller;

import com.example.demo.DTO.*;
import com.example.demo.Entity.ItemVariantDimension;
import com.example.demo.Service.PhItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class PhItemController {

    @Autowired
    private PhItemService phItemService;

    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody PhItemClassDto phItemClassDto) {
        phItemService.add(phItemClassDto);
        return ResponseEntity.ok("Item added successfully");
    }

    @PutMapping("/editItem")
    public ResponseEntity<String> editItem(@RequestBody PhItemClassDto phItemClassDto) throws Exception {
        return phItemService.editItem(phItemClassDto);
    }

    // Get all items with their variants
    @GetMapping("/all")
    public ResponseEntity<List<PhItemClassDto>> getAllItemsWithVariants() {
        List<PhItemClassDto> items = phItemService.getAllItemsWithVariants();
        return ResponseEntity.ok(items);
    }

    //Item Variant Dimension
    @PostMapping("/addVariant")
    public ResponseEntity<ItemVariantDimensionDto> addVariant(@RequestBody ItemVariantDimensionDto newVariantDto) throws Exception {
        ItemVariantDimensionDto createdVariant = phItemService.addVariantToItem(newVariantDto);
        return ResponseEntity.ok(createdVariant);
    }

    // Edit an existing variant
    @PutMapping("/editVariant")
    public ResponseEntity<ItemVariantDimension> editVariant(@RequestBody ItemVariantDimension variant) throws Exception {
        ItemVariantDimension updatedVariant = phItemService.editVariant(variant);
        return ResponseEntity.ok(updatedVariant);
    }

    //Variant Dimension
    @PostMapping("/addDimension")
    public ResponseEntity<VariantDimensionDto> addDimension(@RequestBody VariantDimensionDto variantDimensionDto) {
        VariantDimensionDto createdDimension = phItemService.addDimension(variantDimensionDto);
        return ResponseEntity.ok(createdDimension);
    }

    //Variant Dimension Value
    @PostMapping("/dimensionValues/add")
    public ResponseEntity<VariantDimensionValueDto> addDimensionValue(@RequestBody VariantDimensionValueDto dimensionValueDto) {
        VariantDimensionValueDto createdDimensionValue = phItemService.addDimensionValue(dimensionValueDto);
        return ResponseEntity.ok(createdDimensionValue);
    }

    // item variant dimension value
    @PostMapping("/addVariantDimensionValue")
    public ResponseEntity<List<ItemVariantDimensionValueDto>> addVariantDimensionValue(@RequestBody List<ItemVariantDimensionValueDto> variantDimensionValueDto) {
        List<ItemVariantDimensionValueDto> createdValue = phItemService.addVariantDimensionValues(variantDimensionValueDto);
        return ResponseEntity.ok(createdValue);
    }

    @DeleteMapping("/unselectVariant")
    public ResponseEntity<String> unselectVariant(@RequestBody PhItemClassDto phItemClassDto) {
        phItemService.unselectVariant(phItemClassDto.getItemClassId());
        return ResponseEntity.ok("Variants removed");
    }


}