package com.ccclubs.util.mq.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) 
public @interface size {

	int value();

}
