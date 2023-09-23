package org.admin.catalog.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UseCaseTest {
    @Test
    public void testCreateUseCase() {
        new UseCase();
        Assertions.assertNotNull(new UseCase().execute());
    }
}
