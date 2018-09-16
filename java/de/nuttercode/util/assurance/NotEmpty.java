package de.nuttercode.util.assurance;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;

/**
 * annotated methods guarantee to never return an empty value (e.g. an empty
 * {@link String} or an empty {@link Collection}). annotated fields are
 * guaranteed to never be empty. annotated parameters must never be empty or an
 * IllegalArgumentException will be thrown. Implies {@link NotNull}.
 * 
 * @author Johannes B. Latzel
 *
 */
@Documented
@Retention(SOURCE)
@Target({ FIELD, METHOD, PARAMETER, TYPE_PARAMETER })
public @interface NotEmpty {

}
