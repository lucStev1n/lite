package cs.jou.common.support;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public final class ValidationUtils {
    private ValidationUtils() {}

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Set<ConstraintViolation<T>> validate(T var1, Class<?>... var2) {
        return VALIDATOR.validate(var1, var2);
    }
}
