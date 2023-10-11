package org.admin.catalog.application;

import org.admin.catalog.domain.category.Category;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN in);
}