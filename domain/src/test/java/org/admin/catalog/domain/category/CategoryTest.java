package org.admin.catalog.domain.category;

import org.admin.catalog.domain.exceptions.DomainException;
import org.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Any name";
        final var expectedDescription = "Any description";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.getActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveAnError(){
        final String expectedName = null;
        final var expectedDescription = "Any description";
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveAnError(){
        final String expectedName = "";
        final var expectedDescription = "Any description";
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveAnError(){
        final String expectedName = "an ";
        final var expectedDescription = "Any description";
        final var expectedErrorMessage = "'name' must be have between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveAnError(){
        final String expectedName = "a".repeat(256);
        final var expectedDescription = "Any description";
        final var expectedErrorMessage = "'name' must be have between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenInstantiateACategory(){
        final var expectedName = "Any name";
        final var expectedDescription = "";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.getActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveAnError(){
        final var expectedName = "Any name";
        final var expectedDescription = "Any category";
        final var expectedIsActive = false;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.getActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivate() {
        final var expectedName = "Any name";
        final var expectedDescription = "Any category";
        final var expectedIsActive = false;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, true);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = actualCategory.getUpdatedAt();
        final var createdAt = actualCategory.getCreatedAt();

        Assertions.assertTrue(actualCategory.getActive());
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var deactivateCategory = actualCategory.deactivate();

        Assertions.assertDoesNotThrow(() -> deactivateCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(actualCategory.getId(), deactivateCategory.getId());
        Assertions.assertEquals(actualCategory.getName(), deactivateCategory.getName());
        Assertions.assertEquals(actualCategory.getDescription(), deactivateCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, deactivateCategory.getActive());
        Assertions.assertEquals(deactivateCategory.getCreatedAt(), createdAt);
        Assertions.assertNotNull(deactivateCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(deactivateCategory.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivated() {
        final var expectedName = "Any name";
        final var expectedDescription = "Any category";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, false);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = actualCategory.getUpdatedAt();
        final var createdAt = actualCategory.getCreatedAt();

        Assertions.assertFalse(actualCategory.getActive());
        Assertions.assertNotNull(actualCategory.getDeletedAt());

        final var activatedCategory = actualCategory.activate();

        Assertions.assertDoesNotThrow(() -> activatedCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(actualCategory.getId(), activatedCategory.getId());
        Assertions.assertEquals(actualCategory.getName(), activatedCategory.getName());
        Assertions.assertEquals(actualCategory.getDescription(), activatedCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, activatedCategory.getActive());
        Assertions.assertEquals(activatedCategory.getCreatedAt(), createdAt);
        Assertions.assertNotNull(activatedCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(activatedCategory.getDeletedAt());
    }
}
