package com.github.code13.ap.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Code13
 * @date 2020-09-30 17:46
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface DoctorInterface {
}
