package com.emazon.stock_api_service.domain;


import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.domain.usecase.BrandUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static com.emazon.stock_api_service.util.BrandConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;
    @InjectMocks
    private BrandUseCase brandUseCase;

    @Test
    void testBrandValidateNameAlreadyExists() {
        when(brandUseCase.nameExists("lego")).thenReturn(true);
        Brand brand = new Brand(null,"lego", "description");
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class
                , () -> brandUseCase.validate(brand));
        assertEquals(BRAND_NAME_ALREADY_EXISTS
                ,ex.getErrorList().get(0));
    }
    @Test
    void testBrandValidateNameTooLong(){
        Brand brand = new Brand(null, "a".repeat(51), "description");
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class
                , () -> brandUseCase.validate(brand));
        assertEquals(BRAND_NAME_TOO_LONG
                ,ex.getErrorList().get(0));
    }

    @Test
    void testBrandValidateNameEmpty(){
        Brand brand = new Brand(4L, "", "description");
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class
                , () -> brandUseCase.validate(brand));
        assertEquals(BRAND_NAME_CANNOT_BE_EMPTY
                ,ex.getErrorList().get(0));

    }
    @Test
    void testBrandValidateDescriptionTooLong(){
        Brand brand = new Brand(3L, "lego", "a".repeat(91));
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class
                , () -> brandUseCase.validate(brand));
        assertEquals(BRAND_DESCRIPTION_TOO_LONG
                ,ex.getErrorList().get(0));
    }
    @Test
    void testBrandValidateDescriptionEmpty(){
        Brand brand = new Brand(3L, "lego", "");
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class, () -> brandUseCase.validate(brand));
        assertEquals(BRAND_DESCRIPTION_CANNOT_BE_EMPTY
                ,ex.getErrorList().get(0));
    }
}