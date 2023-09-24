package org.admin.catalog.domain.validation.handler;

import org.admin.catalog.domain.exceptions.DomainException;
import org.admin.catalog.domain.validation.Error;
import org.admin.catalog.domain.validation.ValidationHandler;


public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error error) {
        throw DomainException.with(error);
    }

    @Override
    public ValidationHandler append(final ValidationHandler handler) {
        throw DomainException.with(handler.getErrors());
    }

    @Override
    public ValidationHandler append(final Validation validation) {
        try {
            validation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }

        return this;
    }

    @Override
    public Error getErrors() {
        return new Error("");
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }
}
