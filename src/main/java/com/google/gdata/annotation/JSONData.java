package com.google.gdata.annotation;

public @interface JSONData {
	String value() default "";

	String datePattern() default "";
}