package com.example.ex9.service.impl;

import com.example.ex9.model.dto.InventoryDto;
import com.example.ex9.model.entity.impl.Inventory;
import com.example.ex9.model.entity.impl.Product;
import com.example.ex9.mapper.impl.InventoryMapper;
import com.example.ex9.repository.InventoryRepository;
import com.example.ex9.repository.ProductRepository;
import com.example.ex9.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements BaseService<InventoryDto, Long> {

    private final InventoryRepository inventoryRepository;

    private final ProductRepository productRepository;

    private final InventoryMapper inventoryMapper;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository,
                                ProductRepository productRepository, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public Page<InventoryDto> findByNameContaining(String name, Pageable pageable) {
        Page<Inventory> inventoryPage = inventoryRepository.findAllByNameContaining(name, pageable);
        return inventoryMapper.mapEntityPageIntoDTOPage(pageable, inventoryPage);
    }

    @Override
    public InventoryDto findOne(Long id) {
        Inventory entity =  inventoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Inventory with id="+id+ " not exists"));
        return inventoryMapper.mapEntityIntoDTO(entity);
    }

    @Override
    public Page<InventoryDto> findAll(Pageable pageable) {
        Page<Inventory> inventoryPage = inventoryRepository.findAll(pageable);
        return inventoryMapper.mapEntityPageIntoDTOPage(pageable, inventoryPage);
    }

    @Override
    public InventoryDto create(InventoryDto dto) {
        Inventory entity = inventoryMapper.mapDtoIntoEntity(dto);
        Inventory entityResponse = inventoryRepository.save(entity);
        return inventoryMapper.mapEntityIntoDTO(entityResponse);
    }

    @Override
    @Transactional
    public InventoryDto updateById(Long id, InventoryDto dto) {
        if (dto.getId() != null && !dto.getId().equals(id)) {
            throw new IllegalStateException("Inventory with id=" + dto.getId() + " unlike with " + id);
        }
        InventoryDto inventoryDto = findOne(id);
        Inventory inventory = inventoryMapper.mapDtoIntoEntity(inventoryDto);
        Inventory inventoryRequest = inventoryMapper.mapDtoIntoEntity(dto);

        inventory.setCode(inventoryRequest.getCode());
        inventory.setName(inventoryRequest.getName());
        inventory.setAddress(inventoryRequest.getAddress());

        Inventory inventoryResponse = inventoryRepository.save(inventory);
        return inventoryMapper.mapEntityIntoDTO(inventoryResponse);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        InventoryDto inventoryDto = findOne(id);
        Inventory inventory = inventoryMapper.mapDtoIntoEntity(inventoryDto);
        Optional<List<Product>> productList = productRepository.findProductByInventory(inventory);
        if (productList.isPresent()) {
            for (Product product : productList.get()) {
                productRepository.deleteById(product.getId());
            }
        }
        inventoryRepository.deleteById(id);
        return !inventoryRepository.existsById(id);
    }
}
