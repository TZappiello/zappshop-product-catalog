package com.zappshop.product.catalog.presentation.application.product.query;

import com.zappshop.product.catalog.application.product.query.CategoryMinimalOutput;
import com.zappshop.product.catalog.application.product.query.ProductDetailOutput;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductDetailOutputTestDataBuilder {

    private ProductDetailOutputTestDataBuilder() {
    }

    public static ProductDetailOutput.ProductDetailOutputBuilder aProduct() {
        return ProductDetailOutput.builder()
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
                .description("A Gamer Notebook");
    }

    public static ProductDetailOutput.ProductDetailOutputBuilder aProductAlt1() {
        return  ProductDetailOutput.builder()
                .id(UUID.randomUUID())
                .addedAt(OffsetDateTime.now())
                .name("Desktop I9000")
                .brand("Deep Driver")
                .regularPrice(new BigDecimal(3500.00))
                .salePrice(new BigDecimal(3200.00))
                .inStock(false)
                .enabled(true)
                .category(CategoryMinimalOutput.builder()
                        .id(UUID.randomUUID())
                        .name("Desktop")
                        .build())
                .description("A Gamer Desktop");
    }
}