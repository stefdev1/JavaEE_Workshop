package de.dpunkt.myaktion.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

public class LogQualifier {
	
	@Qualifier
	@Target({ElementType.FIELD, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface TecLog {
		
	}
	
	@Qualifier
	@Target({ElementType.FIELD, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface FachLog {
		
	}


}