package org.admin.catalog.domain.exceptions;

import org.admin.catalog.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStacktraceException {
    private final List<Error> errors;

    private DomainException(final String message, final List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public static DomainException with(final Error errors) {
    return new DomainException(errors.message(), List.of(errors));
    }

    public List<Error> getErrors() {
        return errors;
    }
}
