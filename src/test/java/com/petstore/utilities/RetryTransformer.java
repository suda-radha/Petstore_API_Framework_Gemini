package com.petstore.utilities;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryTransformer implements IAnnotationTransformer {
	@Override
	@SuppressWarnings("rawtypes") // This tells the compiler "I know this is a raw type, it's intentional"
	public void transform(ITestAnnotation annotation, Class testClass, 
	                      Constructor testConstructor, Method testMethod) {
	    annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
