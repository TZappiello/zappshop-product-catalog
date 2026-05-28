package com.zappshop.product.catalog.application.product.query;

import java.util.UUID;

public interface CategoryQueryService {
    CategoryDetailOutput findById(UUID categoryId);
    PageModel<CategoryDetailOutput> filter(Integer size, Integer number);
}
