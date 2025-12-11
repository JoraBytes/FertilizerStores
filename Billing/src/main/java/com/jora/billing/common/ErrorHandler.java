package com.jora.billing.common;

public class ErrorHandler {

	public static String errorTraceForLogger(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.getMessage());
		for (StackTraceElement element : e.getStackTrace()) {
			String className = element.getClassName();
			if (className.startsWith("com.jora")) {
				sb.append("\n");
				sb.append(String.format("at %s.%s(%s: %s)", element.getClassName(), element.getMethodName(),
						element.getFileName(), element.getLineNumber()));
			}
		}
		return sb.toString();
	}

	public static String errorMessage(Throwable e) {
		if (e == null)
			return "Something went wrong";

		Throwable root = e;
		while (root.getCause() != null) {
			root = root.getCause();
		}

		String msg = root.getMessage();
		return (msg == null || msg.isBlank()) ? "Unexpected error occurred" : msg;
	}

}
