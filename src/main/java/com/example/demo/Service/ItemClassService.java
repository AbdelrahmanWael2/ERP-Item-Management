package com.example.demo.Service;

import com.example.demo.DTO.ItemClassDTO;
import com.example.demo.Entity.ItemClass;
import com.example.demo.Repository.ItemClassRepository;
import com.example.demo.Utility.ModelMapperConfig;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemClassService {
    private final ItemClassRepository itemClassRepository;

    private final ModelMapper modelMapper;

    public ItemClassService(ItemClassRepository itemClassRepository, ModelMapper modelMapper) {
        this.itemClassRepository = itemClassRepository;

        this.modelMapper = modelMapper;
    }

    @Transactional
    public ItemClassDTO createItemClass(ItemClassDTO dto) {
        ModelMapper modelMapper = ModelMapperConfig.createModelMapper();

        ItemClass itemClass = modelMapper.map(dto, ItemClass.class);
        itemClass.setActiveFlag(true);

        ItemClass savedItemClass = itemClassRepository.save(itemClass);
        return modelMapper.map(savedItemClass, ItemClassDTO.class);
    }


    @Transactional
    public ItemClassDTO editItemClass(String name, ItemClassDTO dto) {
        ModelMapper modelMapper = ModelMapperConfig.createModelMapper();

        ItemClass itemClass = itemClassRepository.findByName(name)
                .orElseThrow(() -> new NullPointerException("ItemClass not found with name " + name));

        dto.setItemClassId(itemClass.getItemClassId());
        modelMapper.map(dto, itemClass);

        ItemClass updatedItemClass = itemClassRepository.save(itemClass);
        return modelMapper.map(updatedItemClass, ItemClassDTO.class);
    }



    public ItemClass toEntity(ItemClassDTO dto) {
        return modelMapper.map(dto, ItemClass.class);
    }

    public ItemClassDTO toDto(ItemClass itemClass) {
        return modelMapper.map(itemClass, ItemClassDTO.class);
    }

    public void updateEntityFromDTO(ItemClassDTO dto, ItemClass entity) {
        modelMapper.map(dto, entity);
    }
}
