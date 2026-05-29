package com.zappshop.product.catalog.presentation;

import com.zappshop.product.catalog.application.product.management.CategoryManagementApplicationService;
import com.zappshop.product.catalog.application.product.query.CategoryDetailOutput;
import com.zappshop.product.catalog.application.product.management.CategoryInput;
import com.zappshop.product.catalog.application.product.query.CategoryQueryService;
import com.zappshop.product.catalog.application.product.query.PageModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryQueryService categoryQueryService;
    private final CategoryManagementApplicationService categoryManagementApplicationService;

    @PutMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDetailOutput update(@PathVariable UUID productId, @RequestBody @Valid CategoryInput input) {
        categoryManagementApplicationService.update(productId, input);
        return categoryQueryService.findById(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDetailOutput create(@RequestBody @Valid CategoryInput input){
        UUID categoryId = categoryManagementApplicationService.create(input);
        return categoryQueryService.findById(categoryId);
    }

    @GetMapping("{categoryId}")
    public CategoryDetailOutput findById(@PathVariable UUID categoryId){
        return categoryQueryService.findById(categoryId);
    }

    @GetMapping
    public PageModel<CategoryDetailOutput> findAll(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "number", required = false) Integer number
    ){
        return categoryQueryService.filter(size, number);
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID productId){
        categoryManagementApplicationService.disable(productId);
    }
}
