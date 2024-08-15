package com.example.demo.Service;

import com.example.demo.DTO.ItemClassDTO;
import com.example.demo.Entity.ItemClass;
import com.example.demo.Repository.ItemClassRepository;
import com.example.demo.Utility.ItemClassMapper;
import jakarta.transaction.Transactional;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemClassService {
    private final ItemClassRepository itemClassRepository;
    private final ItemClassMapper itemClassMapper = ItemClassMapper.INSTANCE;

    public ItemClassService(ItemClassRepository itemClassRepository) {
        this.itemClassRepository = itemClassRepository;
    }

    @Transactional
    public ItemClassDTO createItemClass(ItemClassDTO dto) {
        ItemClass itemClass = itemClassMapper.toEntity(dto);
        itemClass.setActiveFlag(true);
        itemClass.setCreationDate(new Date());
        itemClass.setModificationDate(new Date());

        ItemClass savedItemClass = itemClassRepository.save(itemClass);
        return itemClassMapper.toDto(savedItemClass);
    }

    @Transactional
    public ItemClass editItemClass(String name, ItemClassDTO dto) {
        ItemClass itemClass = itemClassRepository.findByName(name)
                .orElseThrow(() -> new NullPointerException("ItemClass not found with name " + name));
        dto.setItemClassId(itemClass.getItemClassId());
        itemClassMapper.updateEntityFromDTO(dto, itemClass);
        itemClass.setModificationDate(new Date());
        return itemClassRepository.save(itemClass);
    }
}
