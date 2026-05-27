package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method PUT()

        headers {
            accept 'application/json'
            contentType "application/json"
        }

        urlPath("/api/v1/products/7f1a3b2c-9d8e-4a5b-8c7d-6e5f4a3b2c1d") {
            body([
                    name        : value(
                            test("Notebook X11"),
                            stub(anyNonBlankString())
                    ),
                    brand       : value(
                            test("Deep Driver"),
                            stub(anyNonBlankString())
                    ),
                    regularPrice: value(
                            test(1500.00),
                            stub(anyNumber())
                    ),
                    salePrice   : value(
                            test(1000.00),
                            stub(anyNumber())
                    ),
                    enabled     : value(
                            test(true),
                            stub(anyBoolean())
                    ),
                    categoryId  : value(
                            test("f5ab7a1e-37da-41e1-892b-a1d38275c2f2"),
                            stub(anyUuid())
                    ),
                    description : value(
                            test("A Gamer Notebook"),
                            stub(optional(nonBlank()))
                    )
            ])
        }
    }
    response {
        status 200
        headers {
            contentType 'application/json'
        }
        body([
                id          : fromRequest().path(3),
                addedAt     : anyIso8601WithOffset(),
                name        : anyNonBlankString(),
                brand       : anyNonBlankString(),
                regularPrice: anyNumber(),
                salePrice   : anyNumber(),
                inStock     : anyBoolean(),
                enabled     : anyBoolean(),
                category    : [
                        id  : anyUuid(),
                        name: anyNonBlankString()
                ],
                description : fromRequest().body('$.description'),
        ])
    }
}