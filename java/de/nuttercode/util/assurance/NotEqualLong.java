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
 * annotated methods guarantee to never return the specified value. annotated
 * fields are guaranteed to never be the specified value. annotated parameters
 * must never be the specified value. An {@link IllegalArgumentException} will
 * be thrown otherwise.
 * 
 * @author Johannes B. Latzel
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Documented
@Target({ FIELD, METHOD, PARAMETER, TYPE_PARAMETER })
public @interface NotEqualLong {
	long value();
}
