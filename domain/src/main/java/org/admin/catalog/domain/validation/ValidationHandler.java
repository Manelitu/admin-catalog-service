package org.admin.catalog.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error error);
    ValidationHandler append(ValidationHandler error);
    ValidationHandler append(Validation validation);

    Error getErrors();

    default boolean hasError() {
        return getErrors() != null;
    }

    public interface Validation {
        void validate();
    }
}
