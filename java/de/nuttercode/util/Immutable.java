package de.nuttercode.util;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * annotated classes are immutable
 * 
 * @author Johannes B. Latzel
 *
 */
@Documented
@Retention(SOURCE)
@Target(TYPE)
public @interface Immutable {

}
