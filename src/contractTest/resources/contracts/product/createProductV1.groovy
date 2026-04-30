package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        headers {
            accept 'application/json'
            contentType 'application/json'
        }
        urlPath("/api/v1/products") {
            body([
                    name        : "Notebook X11",
                    brand       : "Deep Driver",
                    regularPrice: 1500.00,
                    salePrice   : 1200.00,
                    enabled     : true,
                    categoryId  : "7f1a3b2c-9d8e-4a5b-8c7d-6e5f4a3b2c1d",
                    description : 'A Gamer Notebook'
            ])
        }
    }
    response {
        status 201
        headers {
            contentType 'application/json'
        }
        body([
                id: anyUuid(),
                addedAt: anyIso8601WithOffset(),
                name        : "Notebook X11",
                brand       : "Deep Driver",
                regularPrice: 1500.00,
                salePrice   : 1200.00,
                inStock     : false,
                enabled     : true,
                category    : [
                        id: "7f1a3b2c-9d8e-4a5b-8c7d-6e5f4a3b2c1d",
                        name: "Gaming"
                ],
                description : 'A Gamer Notebook'
        ])
    }
}