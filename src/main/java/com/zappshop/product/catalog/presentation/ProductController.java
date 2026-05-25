package com.zappshop.product.catalog.presentation;

import com.zappshop.product.catalog.application.product.management.ProductInput;
import com.zappshop.product.catalog.application.product.management.ProductManagementApplicationService;
import com.zappshop.product.catalog.application.product.query.PageModel;
import com.zappshop.product.catalog.application.product.query.ProductDetailOutput;
import com.zappshop.product.catalog.application.product.query.ProductQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductQueryService productQueryService;
    private final ProductManagementApplicationService productManagementApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDetailOutput create(@RequestBody @Valid ProductInput input) {
        UUID productId = productManagementApplicationService.create(input);
        return productQueryService.findById(productId);
    }

    @GetMapping("{productId}")
    public ProductDetailOutput findById(@PathVariable UUID productId) {
        return productQueryService.findById(productId);
    }

    @GetMapping
    public PageModel<ProductDetailOutput> findAll(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "number", required = false) Integer number
    ) {
        return PageModel.<ProductDetailOutput>builder()
                .number(0)
                .size(size)
                .totalElements(2)
                .totalPages(1)
                .content(
                        List.of(
                                ProductDetailOutput.builder()
                                        .id(UUID.randomUUID())
                                        .addedAt(OffsetDateTime.now())
                                        .name("Notebook X11")
                                        .brand("Deep Driver")
                                        .regularPrice(new BigDecimal(1500.00))
                                        .salePrice(new BigDecimal(1200.00))
                                        .inStock(true)
                                        .enabled(true)
                                        .category(CategoryMinimalOutput.builder()
                                                .id(UUID.randomUUID())
                                                .name("Gaming")
                                                .build())
                                        .description("A Gamer Notebook")
                                        .build(),

    }
}
