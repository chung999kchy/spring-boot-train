package com.example.ex9.controller.impl;

import com.example.ex9.controller.BaseController;
import com.example.ex9.model.dto.CategoryDto;
import com.example.ex9.model.dto.ProductDto;
import com.example.ex9.model.responseapi.MsgSuccess;
import com.example.ex9.service.impl.CategoryServiceImpl;
import com.example.ex9.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/admin/products")
public class ProductController extends BaseController<ProductDto, Long> {

    private final ProductServiceImpl productServiceImpl;

    private final CategoryServiceImpl categoryService;

    public ProductController(ProductServiceImpl productServiceImpl, CategoryServiceImpl categoryService) {
        this.productServiceImpl = productServiceImpl;
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/top")
    public ResponseEntity<MsgSuccess> getTop10Sell() {
        List<ProductDto> tList = productServiceImpl.findTopSell();
        return new ResponseEntity<>(new MsgSuccess(1, 10L, 10, tList), HttpStatus.OK);
    }

    @GetMapping(path = "/category/{id}")
    public ResponseEntity<MsgSuccess> getByCategory(@PathVariable("id") Long id,
                                                    @RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10") int limit,
                                                    @RequestParam(defaultValue = "ASC") String sortDirection,
                                                    @RequestParam(defaultValue = "id") String sortBy) {
        Sort.Direction direction = sortDirection.
                equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page-1, limit, sort);

        CategoryDto categoryDto = categoryService.findOne(id);
        Page<ProductDto> tPage = productServiceImpl.findByCategory(categoryDto, pageable);
        List<ProductDto> tList = tPage.getContent();
        return new ResponseEntity<>(new MsgSuccess(page, tPage.getTotalElements(), limit, tList), HttpStatus.OK);
    }
}

