package com.example.ex9.service.impl;

import com.example.ex9.model.dto.CategoryDto;
import com.example.ex9.model.entity.impl.Category;
import com.example.ex9.model.entity.impl.Product;
import com.example.ex9.mapper.impl.CategoryMapper;
import com.example.ex9.repository.CategoryRepository;
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
public class CategoryServiceImpl implements BaseService<CategoryDto, Long> {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Page<CategoryDto> findByNameContaining(String name, Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAllByNameContaining(name, pageable);
        return categoryMapper.mapEntityPageIntoDTOPage(pageable, categoryPage);
    }

    @Override
    public CategoryDto findOne(Long id) {
        Category entity =  categoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Category with id="+id+ " not exists"));
        return categoryMapper.mapEntityIntoDTO(entity);
    }

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryMapper.mapEntityPageIntoDTOPage(pageable, categoryPage);
    }

    @Override
    public CategoryDto create(CategoryDto dto) {
        Category entity = categoryMapper.mapDtoIntoEntity(dto);
        Category entityResponse = categoryRepository.save(entity);
        return categoryMapper.mapEntityIntoDTO(entityResponse);
    }

    @Override
    @Transactional
    public CategoryDto updateById(Long id, CategoryDto dto) {
        if (dto.getId() != null && !dto.getId().equals(id)) {
            throw new IllegalStateException("Category with id=" + dto.getId() + " unlike with " + id);
        }
        CategoryDto categoryDto = findOne(id);
        Category category = categoryMapper.mapDtoIntoEntity(categoryDto);
        Category categoryRequest = categoryMapper.mapDtoIntoEntity(dto);

        category.setCode(categoryRequest.getCode());
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category categoryResponse = categoryRepository.save(category);
        return categoryMapper.mapEntityIntoDTO(categoryResponse);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        CategoryDto categoryDto = findOne(id);
        Category category = categoryMapper.mapDtoIntoEntity(categoryDto);
        Optional<List<Product>> productList = productRepository.findProductByCategory(category);
        if (productList.isPresent()) {
            for (Product product : productList.get()) {
                productRepository.deleteById(product.getId());
            }
        }
        categoryRepository.deleteById(id);
        return !categoryRepository.existsById(id);
    }
}
