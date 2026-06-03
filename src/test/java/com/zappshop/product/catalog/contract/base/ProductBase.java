package com.zappshop.product.catalog.contract.base;

import com.zappshop.product.catalog.application.ResoucerNotFoundExcetion;
import com.zappshop.product.catalog.presentation.ProductController;
import com.zappshop.product.catalog.application.product.management.ProductInput;
import com.zappshop.product.catalog.application.product.management.ProductManagementApplicationService;
import com.zappshop.product.catalog.application.product.query.PageModel;
import com.zappshop.product.catalog.application.product.query.ProductDetailOutput;
import com.zappshop.product.catalog.presentation.application.product.query.ProductDetailOutputTestDataBuilder;
import com.zappshop.product.catalog.application.product.query.ProductQueryService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.templates.TemplateFormat;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@WebMvcTest(controllers = ProductController.class)
@ExtendWith(RestDocumentationExtension.class)
class ProductBase {

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private ProductQueryService productQueryService;

    @MockitoBean
    private ProductManagementApplicationService productManagementApplicationService;

    public static final UUID validProductId = UUID.fromString("7f1a3b2c-9d8e-4a5b-8c7d-6e5f4a3b2c1d");
    public static final UUID invalidProductId = UUID.fromString("21651a12-b126-4213-ac21-19f66ff4642e");

    @BeforeEach
    void setUp(RestDocumentationContextProvider documentationContextProvider) {
        RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(context)
                        .apply(documentationConfiguration(documentationContextProvider)
                                .snippets().withTemplateFormat(TemplateFormats.asciidoctor())
                                .and().operationPreprocessors()
                                .withResponseDefaults(Preprocessors.prettyPrint()))
                        .alwaysDo(MockMvcRestDocumentation.document("{ClassName}/{methodName}"))
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8).build());

        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();

        mockValidProductFindById();
        mockFilterProducts();
        mockCreateProduct();
        mockInvalidProductFindById();
        mockUpdateProduct();
        mockDeletedProduct();
    }


    private void mockDeletedProduct() {
        Mockito.doNothing().when(productManagementApplicationService).disable(any(UUID.class));
    }

    private void mockUpdateProduct() {
        Mockito.doNothing().when(productManagementApplicationService)
                .update(eq(validProductId), any(ProductInput.class));

    }

    private void mockInvalidProductFindById() {
        Mockito.when(productQueryService.findById(invalidProductId))
                .thenThrow(new ResoucerNotFoundExcetion());
    }

    private void mockCreateProduct() {
        Mockito.when(productManagementApplicationService.create(any(ProductInput.class)))
                .thenReturn(validProductId);
    }

    private void mockFilterProducts() {
        Mockito.when(productQueryService.filter(
                        Mockito.anyInt(), Mockito.anyInt()))
                .then((answer) -> {
                    Integer size = answer.getArgument(0);

                    return PageModel.<ProductDetailOutput>builder()
                            .number(0)
                            .size(size)
                            .totalElements(2)
                            .totalPages(1)
                            .content(
                                    List.of(
                                            ProductDetailOutputTestDataBuilder.aProduct().build(),
                                            ProductDetailOutputTestDataBuilder.aProductAlt1().build()
                                    )
                            ).build();
                });
    }

    private void mockValidProductFindById() {
        Mockito.when(productQueryService.findById(validProductId))
                .thenReturn(ProductDetailOutputTestDataBuilder.aProduct().id(validProductId).build());
    }


}






































