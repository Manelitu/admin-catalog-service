package org.admin.catalog.application;

import org.admin.catalog.domain.category.Category;

public abstract class UnitUseCase<IN> {
    public abstract void execute(IN in);
}