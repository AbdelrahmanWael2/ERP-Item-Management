package com.example.demo.Service;

import com.example.demo.DTO.PhItemClassDto;
import com.example.demo.Entity.PhItem;
import com.example.demo.Repository.PhItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhItemService {

    @Autowired
    private PhItemRepository phItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PhItemClassDto editItem(PhItemClassDto phItemClassDto) throws Exception {
        PhItem existingItem = phItemRepository.findById(phItemClassDto.getItemSid())
                .orElseThrow(() -> new Exception("Item not found with id: " + phItemClassDto.getItemSid()));

        modelMapper.map(phItemClassDto, existingItem);
        PhItem savedItem = phItemRepository.save(existingItem);
        return modelMapper.map(savedItem, PhItemClassDto.class);
    }
}
