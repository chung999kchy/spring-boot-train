package com.example.ex9.controller.impl;

import com.example.ex9.controller.BaseController;
import com.example.ex9.model.dto.InventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "api/admin/inventories")
public class InventoryController extends BaseController<InventoryDto, Long> {

}
