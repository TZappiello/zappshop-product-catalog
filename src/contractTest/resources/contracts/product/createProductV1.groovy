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
                    name        : value(
                            test("Notebook X11"),
                            stub(nonBlank())
                    ),
                    brand       : value(
                            test("Deep Driver"),
                            stub(nonBlank())
                    ),
                    regularPrice: value(
                            test(1500.00),
                            stub(number())
                    ),
                    salePrice   : value(
                            test(1200.00),
                            stub(number())
                    ),
                    enabled     : value(
                            test(false),
                            stub(anyBoolean())
                    ),
                    categoryId  : value(
                            test("7f1a3b2c-9d8e-4a5b-8c7d-6e5f4a3b2c1d"),
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