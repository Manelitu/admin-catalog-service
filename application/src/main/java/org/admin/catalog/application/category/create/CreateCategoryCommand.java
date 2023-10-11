package org.admin.catalog.application.category.create;

public record CreateCategoryCommand(
        String name,
        String description,
        boolean active
) {
    public static CreateCategoryCommand with(
            final String name,
            final String description,
            final boolean active
    ) {
        return new CreateCategoryCommand(name, description, active);
    }
}
