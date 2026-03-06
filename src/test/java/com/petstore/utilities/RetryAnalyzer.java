package com.petstore.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	private int count = 0;
	private static final int MAX_RETRY_COUNT = 3; // Number of times to retry

	@Override
	public boolean retry(ITestResult result) {
		if (count < MAX_RETRY_COUNT) {
			count++;
			return true; // Tells TestNG to run the test again
		}
		return false;
	}

}
