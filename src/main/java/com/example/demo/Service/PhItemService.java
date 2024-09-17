package com.example.demo.Service;

import com.example.demo.DTO.*;
import com.example.demo.DTO.DimensionValueDto;
import com.example.demo.Entity.*;
import com.example.demo.Enums.ItemTypeEnums;
import com.example.demo.Repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
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


    @Transactional
    public void unselectVariant(Integer sid) {

        Optional<PhItem> itemFound = phItemRepository.findById(sid);
        Integer itemClassId = itemFound.get().getItemClassId();

        // find items by class id
        List<PhItem> phItems = phItemRepository.findByItemClassId(itemClassId);


        for (PhItem phItem : phItems) {



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
            itemVariantDimensionRepository.deleteAllByItemClassId(phItem.getItemClassId());
            itemVariantDimensionValueRepository.deleteAllByItemClassId(phItem.getItemClassId());
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

    @Transactional
    public void saveFullItem(FullItemDto fullItemDto) throws Exception {

        //  Save PhItem
        PhItem phItem = modelMapper.map(fullItemDto, PhItem.class);
        phItem = phItemRepository.save(phItem);

        // create and save ItemType
        ItemType itemType = new ItemType();
        itemType.setSid(phItem.getItemSid());
        itemType.setName(ItemTypeEnums.ItemTypes.ITEM.toString());
        itemType.setNameEn(ItemTypeEnums.ItemTypes.ITEM.toString());
        phItemRepository.updateItemTypeToZero(phItem.getItemSid());
        itemTypeRepository.save(itemType);

        if (fullItemDto.getHasVariants() == 1 && fullItemDto.getDimensions() != null) {
            for (DimensionDto dimensionDto : fullItemDto.getDimensions()) {

                // save VariantDimension
                VariantDimension variantDimension = modelMapper.map(dimensionDto, VariantDimension.class);
                // remove dimension values before saving VariantDimension
                List<VariantDimensionValue> extractedValues;
                extractedValues = variantDimension.getDimensionValues();
                variantDimension.setDimensionValues(null);
                variantDimensionRepository.save(variantDimension);

                if (dimensionDto.getDimensionValues() != null) {
                    for (DimensionValueDto dimensionValueDto : dimensionDto.getDimensionValues()) {
                        VariantDimensionValue variantDimensionValue = new VariantDimensionValue();
                        VariantDimensionValuePK valuePk = new VariantDimensionValuePK();
                        valuePk.setDimensionId(variantDimension.getDimensionId());
                        valuePk.setValueSer(dimensionValueDto.getValueSer());
                        variantDimensionValue.setId(valuePk);
                        variantDimensionValue.setValueCode(dimensionValueDto.getValueSer().toString());
                        variantDimensionValue.setValueNameEn(dimensionValueDto.getValueNameEn());
                        variantDimensionValue.setValueNameAr(dimensionValueDto.getValueNameAr());
                        variantDimensionValue.setActiveFlag(dimensionValueDto.getDefaultValue());
                        variantDimensionValue.setCreatedBy("system");
                        variantDimensionValue.setCreationDate(new Date());
                        variantDimensionValue.setDimention(variantDimension);
                        variantDimensionValueRepository.save(variantDimensionValue);
                        extractedValues.add(variantDimensionValue);
                    }
                }

                //save ItemVariantDimension
                ItemVariantDimension itemVariantDimension = new ItemVariantDimension();
                ItemVariantDimensionPK pk = new ItemVariantDimensionPK();
                pk.setItemSid(phItem.getItemSid());
                pk.setDimensionId(variantDimension.getDimensionId());
                itemVariantDimension.setId(pk);
                itemVariantDimension.setItem(phItem);
                itemVariantDimension.setItemClassId(phItem.getItemClassId());
                itemVariantDimension.setItemCode(phItem.getItemCode());
                itemVariantDimensionRepository.save(itemVariantDimension);

                // dimension values
                if (dimensionDto.getDimensionValues() != null) {
                    for (DimensionValueDto dimensionValueDto : dimensionDto.getDimensionValues()) {

                        ItemVariantDimensionValue dimensionValue = new ItemVariantDimensionValue();
                        ItemVariantDimensionValuePK valuePk = new ItemVariantDimensionValuePK();
                        valuePk.setDimensionId(pk.getDimensionId());
                        valuePk.setItemSid(phItem.getItemSid());
                        valuePk.setValueSer(dimensionValueDto.getValueSer());
                        dimensionValue.setId(valuePk);
                        dimensionValue.setItemVariantDimension(itemVariantDimension);
                        dimensionValue.setAmountAdded(dimensionValueDto.getAmountAdded());
                        dimensionValue.setDefaultValue(dimensionValueDto.getDefaultValue());
                        dimensionValue.setValueNameEn(dimensionValueDto.getValueNameEn());
                        dimensionValue.setValueNameAr(dimensionValueDto.getValueNameAr());
                        dimensionValue.setItemClassId(phItem.getItemClassId());
                        dimensionValue.setItem(phItem);
                        itemVariantDimensionValueRepository.save(dimensionValue);
                    }
                }
            }
        }
        if (fullItemDto.getHasVariants() == 1) {
            assert fullItemDto.getDimensions() != null;
            saveVariantCombinations(phItem, fullItemDto.getDimensions());
        }
    }

    @Transactional
    public void saveVariantCombinations(PhItem coreItem, List<DimensionDto> dimensions) {
        // get all dimension values
        List<List<DimensionValueDto>> dimensionValues = new ArrayList<>();
        for (DimensionDto dimension : dimensions) {
            if (dimension.getDimensionValues() != null) {
                dimensionValues.add(dimension.getDimensionValues());
            }
        }

        // Generate all combinations of dimension values
        List<List<DimensionValueDto>> combinations = getCombinations(dimensionValues);

        String coreItemNameEn = coreItem.getNameEn();
        String coreItemNameAr = coreItem.getNameAr();

        for (List<DimensionValueDto> combination : combinations) {
            PhItem variantItem = new PhItem();
            variantItem.setItemClassId(coreItem.getItemClassId());
            variantItem.setItemCode(generateVariantItemCode(coreItem, combination));

            variantItem.setNameEn(generateVariantItemName(coreItemNameEn, combination, "en"));
            variantItem.setNameAr(generateVariantItemName(coreItemNameAr, combination, "ar"));
            System.out.println("Name AR length: " + variantItem.getNameAr());

            variantItem.setHasVariants((byte) 0);
            variantItem.setItemType(coreItem.getItemType());
            variantItem = phItemRepository.save(variantItem);

            ItemType variantItemType = new ItemType();
            variantItemType.setSid(variantItem.getItemSid());
            if(coreItem.getItemType() == 1){
                variantItemType.setName(ItemTypeEnums.ItemTypes.VARIANT.toString());
                variantItemType.setNameEn(ItemTypeEnums.ItemTypes.VARIANT.toString());
            }else{
                variantItemType.setName(ItemTypeEnums.ItemTypes.TEMPLATE.toString());
                variantItemType.setNameEn(ItemTypeEnums.ItemTypes.TEMPLATE.toString());
            }

            itemTypeRepository.save(variantItemType);
        }
    }

    private List<List<DimensionValueDto>> getCombinations(List<List<DimensionValueDto>> lists) {
        List<List<DimensionValueDto>> result = new ArrayList<>();
        if (lists == null || lists.isEmpty()) {
            return result;
        }
        getCombinationsRecursive(lists, result, new ArrayList<>(), 0);
        return result;
    }

    private void getCombinationsRecursive(List<List<DimensionValueDto>> lists, List<List<DimensionValueDto>> result, List<DimensionValueDto> current, int depth) {
        if (depth == lists.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (DimensionValueDto value : lists.get(depth)) {
            current.add(value);
            getCombinationsRecursive(lists, result, current, depth + 1);
            current.remove(current.size() - 1);
        }
    }

    private String generateVariantItemName(String baseName, List<DimensionValueDto> combination, String lang) {
        StringBuilder name = new StringBuilder(baseName);


        for (DimensionValueDto value : combination) {
            if (lang.equals("en")) {
                name.append(" ").append(value.getValueNameEn());
            } else if (lang.equals("ar")) {
                name.append(" ").append(value.getValueNameAr());
            }

        }
        return name.toString();
    }

    private String generateVariantItemCode(PhItem coreItem, List<DimensionValueDto> combination) {
        StringBuilder code = new StringBuilder(coreItem.getItemCode());
        for (DimensionValueDto value : combination) {
            code.append("-").append(value.getValueSer());
        }
        return code.toString();
    }


}

