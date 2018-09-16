package de.nuttercode.util.assurance;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * annotated methods guarantee to never return values outside of [begin, end].
 * annotated fields are guaranteed to never be outside of [begin, end].
 * annotated parameters must never be outside of [begin, end] or an
 * IllegalArgumentException will be thrown.
 * 
 * @author Johannes B. Latzel
 *
 */
@Documented
@Retention(SOURCE)
@Target({ FIELD, METHOD, PARAMETER, TYPE_PARAMETER })
public @interface InLongRange {
	long begin();

	long end();

}
