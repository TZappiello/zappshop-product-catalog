package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        headers {
            accept 'application/json'
        }
        url("/api/v1/products/7f1a3b2c-9d8e-4a5b-8c7d-6e5f4a3b2c1d")
    }
    response {
        status 200
        headers {
            contentType 'application/json'
        }
        body([
                id: fromRequest().path(3),
                addedAt: anyIso8601WithOffset(),
                name: "Notebook X11",
                brand: "Deep Driver",
                regularPrice: 1500.00,
                salePrice: 1200.00,
                inStock: false,
                enabled: true,
                categoryId: anyUuid(),
                description: 'A Gamer Notebook'
        ])

    }
}