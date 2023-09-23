package org.admin.catalog.application;

import org.admin.catalog.domain.Category;

public class UseCase {
    public Category execute() {
        return new Category();
    }
}