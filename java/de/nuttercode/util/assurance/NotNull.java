package de.nuttercode.util.assurance;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotated methods guarantee to never return null. annotated fields are
 * guaranteed to never be null. annotated parameters must never be null or an
 * {@link NullPointerException} will be thrown.
 * 
 * @author Johannes B. Latzel
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Documented
@Target({ FIELD, METHOD, PARAMETER, TYPE_PARAMETER })
public @interface NotNull {

}
