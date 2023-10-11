package org.admin.catalog.application.category.create;

import org.admin.catalog.domain.category.Category;
import org.admin.catalog.domain.category.CategoryGateway;
import org.admin.catalog.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {
    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand command) {
        final var name = command.name();
        final var description = command.description();
        final var active = command.active();

        final var category = Category.newCategory(name, description, active);

        category.validate(new ThrowsValidationHandler());



        return CreateCategoryOutput.from(this.categoryGateway.create(category));
    }
}
