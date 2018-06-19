package com.ccclubs.util.struct;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) 
public @interface Pad {
	PadType type();
}
