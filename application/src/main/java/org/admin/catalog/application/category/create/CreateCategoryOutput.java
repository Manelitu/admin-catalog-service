package org.admin.catalog.application.category.create;

import org.admin.catalog.domain.category.Category;
import org.admin.catalog.domain.category.CategoryID;

public record CreateCategoryOutput(CategoryID id) {
    public static CreateCategoryOutput from(final Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
