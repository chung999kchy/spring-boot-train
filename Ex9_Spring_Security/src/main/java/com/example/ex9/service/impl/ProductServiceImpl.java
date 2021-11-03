package com.example.ex9.service.impl;

import com.example.ex9.mapper.impl.CategoryMapper;
import com.example.ex9.model.dto.CategoryDto;
import com.example.ex9.model.dto.ProductDto;
import com.example.ex9.model.entity.impl.Category;
import com.example.ex9.model.entity.impl.Product;
import com.example.ex9.mapper.impl.ProductMapper;
import com.example.ex9.repository.ProductRepository;
import com.example.ex9.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements BaseService<ProductDto, Long> {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryMapper categoryMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Page<ProductDto> findByNameContaining(String name, Pageable pageable) {
        Page<Product> productPage = productRepository.findAllByNameContaining(name, pageable);
        return productMapper.mapEntityPageIntoDTOPage(pageable, productPage);
    }

    @Override
    public ProductDto findOne(Long id) {
        Product entity =  productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Product with id="+id+ " not exists"));
        return productMapper.mapEntityIntoDTO(entity);
    }

    public Page<ProductDto> findByCategory(CategoryDto categoryDto, Pageable pageable) {
        Category category = categoryMapper.mapDtoIntoEntity(categoryDto);
        Page<Product> productPage =  productRepository.findProductByCategory(category, pageable);
        return productMapper.mapEntityPageIntoDTOPage(pageable, productPage);
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productMapper.mapEntityPageIntoDTOPage(pageable, productPage);
    }

    public List<ProductDto> findTopSell() {
        List<Product> productList = productRepository.findTop10ByOrderByAmountSaleDesc();
        return productMapper.mapEntitiesIntoDTOs(productList);
    }

    @Override
    public ProductDto create(ProductDto dto) {
        Product entity = productMapper.mapDtoIntoEntity(dto);
        Product entityResponse = productRepository.save(entity);
        return productMapper.mapEntityIntoDTO(entityResponse);
    }

    @Override
    @Transactional
    public ProductDto updateById(Long id, ProductDto dto) {
        if (dto.getId() != null && !dto.getId().equals(id)) {
            throw new IllegalStateException("Product with id=" + dto.getId() + " unlike with " + id);
        }
        ProductDto productDto = findOne(id);
        Product product = productMapper.mapDtoIntoEntity(productDto);
        Product productRequest = productMapper.mapDtoIntoEntity(dto);

        product.setName(productRequest.getName());
        product.setName(productRequest.getCode());
        product.setDescription(productRequest.getDescription());
        product.setImage(productRequest.getImage());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setAmountSale(productRequest.getAmountSale());
        product.setCategory(productRequest.getCategory());
        product.setInventory(productRequest.getInventory());

        Product productResponse = productRepository.save(product);
        return productMapper.mapEntityIntoDTO(productResponse);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        productRepository.deleteById(id);
        return !productRepository.existsById(id);
    }
}
