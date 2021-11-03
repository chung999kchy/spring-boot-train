package com.example.ex9.controller.impl;


import com.example.ex9.controller.BaseController;
import com.example.ex9.model.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "api/admin/categories")
@CrossOrigin(origins = "*")
public class CategoryController extends BaseController<CategoryDto, Long> {

}
