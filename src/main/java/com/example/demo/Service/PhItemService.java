package com.example.demo.Service;

import com.example.demo.DTO.*;
import com.example.demo.Entity.*;
import com.example.demo.Enums.ItemTypeEnums;
import com.example.demo.Repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhItemService {

    @Autowired
    private PhItemRepository phItemRepository;

    @Autowired
    private ItemVariantDimensionRepository itemVariantDimensionRepository;

    @Autowired
    private VariantDimensionRepository variantDimensionRepository;

    @Autowired
    private ItemVariantDimensionValueRepository itemVariantDimensionValueRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VariantDimensionValueRepository variantDimensionValueRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    public void add(PhItemClassDto phItemClassDto) {
        PhItem phItem = modelMapper.map(phItemClassDto, PhItem.class);
        ItemType itemType = new ItemType();
        itemType.setSid(phItem.getItemSid());
        itemType.setName(ItemTypeEnums.ItemTypes.ITEM.toString());
        itemType.setNameEn(ItemTypeEnums.ItemTypes.ITEM.toString());
        itemTypeRepository.save(itemType);
        phItemRepository.save(phItem);
    }

    public ResponseEntity<String> editItem(PhItemClassDto phItemClassDto) throws Exception {
        List<PhItem> existingItem = phItemRepository.findByItemClassId(phItemClassDto.getItemClassId());
        for (PhItem phItem : existingItem) {
            Optional<ItemType> type = itemTypeRepository.findBySid(phItem.getItemSid());
            if (type.isPresent()) {
                PhItem savedItem = phItemRepository.save(phItem);
                return ResponseEntity.ok("Item edited");
            }
        }
        return ResponseEntity.ok("Item doesn't exist");
    }

    // Get all items along with their variants
    public List<PhItemClassDto> getAllItemsWithVariants() {
        List<PhItem> items = phItemRepository.findAll();
        List<PhItemClassDto> newItems = new ArrayList<>();

        for (PhItem item : items) {
            PhItemClassDto itemDto = modelMapper.map(item, PhItemClassDto.class);
            newItems.add(itemDto);
        }

        return newItems;
    }

    // Item Variant Dimension
    public ItemVariantDimensionDto addVariantToItem(ItemVariantDimensionDto newVariantDto) throws Exception {

        // Get the PhItems using the given ItemClassId from dto
        List<PhItem> items = phItemRepository.findByItemClassId(newVariantDto.getItemClassId());

        if (items.isEmpty()) {
            throw new Exception("Item not found with itemClassId: " + newVariantDto.getItemClassId());
        }

        PhItem item = items.get(0);

        // Get VariantDimension using dimensionId
        VariantDimension dimension = variantDimensionRepository.findById(newVariantDto.getDimensionId())
                .orElseThrow(() -> new RuntimeException("Dimension not found with id: " + newVariantDto.getDimensionId()));

        ItemVariantDimension newVariant = modelMapper.map(newVariantDto, ItemVariantDimension.class);

        ItemVariantDimensionPK compositeKey = new ItemVariantDimensionPK();
        compositeKey.setItemSid(item.getItemSid());
        compositeKey.setDimensionId(dimension.getDimensionId());

        newVariant.setId(compositeKey);

        newVariant.setItem(item);
        ItemVariantDimension savedVariant = itemVariantDimensionRepository.save(newVariant);

        return modelMapper.map(savedVariant, ItemVariantDimensionDto.class);
    }


    // Edit an existing variant
    public ItemVariantDimension editVariant(ItemVariantDimension variant) throws Exception {
        ItemVariantDimension existingVariant = itemVariantDimensionRepository.findById(variant.getId())
                .orElseThrow(() -> new Exception("Variant not found with id: " + variant.getId()));
        modelMapper.map(variant, existingVariant);
        return itemVariantDimensionRepository.save(existingVariant);
    }

    // Variant Dimension
    public VariantDimensionDto addDimension(VariantDimensionDto variantDimensionDto) {
        VariantDimension variantDimension = modelMapper.map(variantDimensionDto, VariantDimension.class);
        VariantDimension savedDimension = variantDimensionRepository.save(variantDimension);

        return modelMapper.map(savedDimension, VariantDimensionDto.class);
    }

    public List<VariantDimensionDto> getAllDimensions() {
        List<VariantDimension> dimensions = variantDimensionRepository.findAll();
        return dimensions.stream()
                .map(d -> modelMapper.map(d, VariantDimensionDto.class))
                .collect(Collectors.toList());
    }

    // Variant Dimension Value
    public VariantDimensionValueDto addDimensionValue(VariantDimensionValueDto dimensionValueDto) {
        VariantDimensionValue dimensionValue = modelMapper.map(dimensionValueDto, VariantDimensionValue.class);

        // Checking if the dimension exists before we add the value
        VariantDimension dimension = variantDimensionRepository.findById(dimensionValueDto.getDimensionId())
                .orElseThrow(() -> new RuntimeException("Dimension not found with id: " + dimensionValueDto.getDimensionId()));

        dimensionValue.setDimention(dimension);

        VariantDimensionValuePK pk = new VariantDimensionValuePK();
        pk.setDimensionId(dimensionValueDto.getDimensionId());
        pk.setValueSer(dimensionValueDto.getValueSer());
        dimensionValue.setId(pk);

        VariantDimensionValue savedDimensionValue = variantDimensionValueRepository.save(dimensionValue);

        return modelMapper.map(savedDimensionValue, VariantDimensionValueDto.class);
    }

    // Item Variant Dimension Value
    public List<ItemVariantDimensionValueDto> addVariantDimensionValues(List<ItemVariantDimensionValueDto> variantDimensionValueDtos) {
        if (variantDimensionValueDtos.isEmpty()) {
            throw new IllegalArgumentException("The list of dimension values is empty.");
        }

        List<ItemVariantDimensionValueDto> savedDimensionValueDtos = new ArrayList<>();

        int itemClassId = variantDimensionValueDtos.get(0).getItemClassId();
        List<PhItem> phItems = phItemRepository.findByItemClassId(itemClassId);

        if (phItems.isEmpty()) {
            throw new RuntimeException("No PhItem found with itemClassId: " + itemClassId);
        }

        // Mark items as having variants
        for (PhItem phItem : phItems) {
            phItem.setHasVariants((byte) 1);
        }

        PhItem originalPhItem = phItems.stream()
                .filter(item -> itemTypeRepository.findBySidAndNameEn(item.getItemSid(), String.valueOf(ItemTypeEnums.ItemTypes.ITEM)).isPresent())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No original PhItem found with itemClassId: " + itemClassId));

        for (ItemVariantDimensionValueDto variantDimensionValueDto : variantDimensionValueDtos) {
            ItemVariantDimension itemVariantDimension = itemVariantDimensionRepository
                    .findById_dimensionId(variantDimensionValueDto.getDimensionId())
                    .orElseThrow(() -> new RuntimeException("ItemVariantDimension not found with DimensionID: " +
                            variantDimensionValueDto.getDimensionId()));

            if (itemVariantDimension == null || itemVariantDimension.getItemClassId() != itemClassId) {
                // Skip
                continue;
            }

            ItemVariantDimensionValue dimensionValue = modelMapper.map(variantDimensionValueDto, ItemVariantDimensionValue.class);

            dimensionValue.setItemVariantDimension(itemVariantDimension);
            dimensionValue.setItem(originalPhItem);

            ItemVariantDimensionValuePK pk = new ItemVariantDimensionValuePK();
            pk.setDimensionId(variantDimensionValueDto.getDimensionId());
            pk.setItemSid(originalPhItem.getItemSid());

            dimensionValue.setId(pk);

            ItemVariantDimensionValue savedDimensionValue = itemVariantDimensionValueRepository.save(dimensionValue);

            savedDimensionValueDtos.add(modelMapper.map(savedDimensionValue, ItemVariantDimensionValueDto.class));
        }
        List<ItemVariantDimensionValue> dimensionValues = itemVariantDimensionValueRepository
                .findByItemSid(originalPhItem.getItemSid());
        dimensionValues.sort(Comparator.comparingInt(d -> d.getId().getValueSer()));

        PhItem newPhItem = getPhItem(dimensionValues, originalPhItem);

        phItemRepository.save(newPhItem);

        ItemType itemType = new ItemType();
        itemType.setSid(newPhItem.getItemSid());
        itemType.setName(ItemTypeEnums.ItemTypes.VARIANT.toString());
        itemType.setNameEn(ItemTypeEnums.ItemTypes.VARIANT.toString());
        itemTypeRepository.save(itemType);

        return savedDimensionValueDtos;
    }


    private static PhItem getPhItem(List<ItemVariantDimensionValue> dimensionValues, PhItem originalPhItem) {
        StringBuilder nameEnBuilder = new StringBuilder();
        StringBuilder nameArBuilder = new StringBuilder();

        for (ItemVariantDimensionValue value : dimensionValues) {
            String dimensionNameEn = value.getValueNameEn();
            String dimensionNameAr = value.getValueNameAr();

            if (dimensionNameEn != null) {
                nameEnBuilder.insert(0, dimensionNameEn + " ");
            }
            if (dimensionNameAr != null) {
                nameArBuilder.insert(0, dimensionNameAr + " ");
            }
        }

        PhItem newPhItem = new PhItem();
        newPhItem.setNameEn(nameEnBuilder.toString().trim() + " " + originalPhItem.getNameEn());
        newPhItem.setNameAr(originalPhItem.getNameAr() + " " + nameArBuilder.toString().trim());
        newPhItem.setItemClassId(originalPhItem.getItemClassId());
        newPhItem.setHasVariants((byte) 1);
        return newPhItem;
    }


    @Transactional
    public void unselectVariant(Integer itemClassId) {
        List<PhItem> phItems = phItemRepository.findByItemClassId(itemClassId);

        for (PhItem phItem : phItems) {

            itemVariantDimensionRepository.deleteAllByItemClassId(phItem.getItemClassId());
            itemVariantDimensionValueRepository.deleteAllByItemClassId(phItem.getItemClassId());

            Optional<ItemType> optionalItemType = itemTypeRepository.findBySidAndNameEn(phItem.getItemSid(),
                    String.valueOf(ItemTypeEnums.ItemTypes.VARIANT));

            Optional<ItemType> optionalItemType2 = itemTypeRepository.findBySidAndNameEn(phItem.getItemSid(),
                    String.valueOf(ItemTypeEnums.ItemTypes.ITEM));

            if (optionalItemType.isPresent()) {
                List<ItemVariantDimensionValue> dimensionValues = itemVariantDimensionValueRepository.findByItemSid(phItem.getItemSid());
                itemVariantDimensionValueRepository.deleteAll(dimensionValues);

                List<ItemVariantDimension> variantDimensions = itemVariantDimensionRepository.findByItemSid(phItem.getItemSid());
                itemVariantDimensionRepository.deleteAll(variantDimensions);

                itemTypeRepository.delete(optionalItemType.get());

                phItemRepository.delete(phItem);

            } else if (optionalItemType2.isPresent()) {
                phItem.setHasVariants((byte) 0);
            }
        }

        Optional<PhItem> originalItem = phItemRepository.findByItemClassId(itemClassId).stream()
                .filter(item -> itemTypeRepository.findBySidAndNameEn(item.getItemSid(),
                        String.valueOf(ItemTypeEnums.ItemTypes.ITEM)).isPresent())
                .findFirst();

        if (originalItem.isPresent()) {
            PhItem itemToUpdate = originalItem.get();
            itemToUpdate.setHasVariants((byte) 0);
            phItemRepository.save(itemToUpdate);
        } else {
            throw new RuntimeException("Original item with itemClassId: " + itemClassId + " not found.");
        }
    }


}

