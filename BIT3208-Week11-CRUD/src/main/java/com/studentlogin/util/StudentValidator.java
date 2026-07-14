package com.studentlogin.util;

import java.util.regex.Pattern;

public final class StudentValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    private StudentValidator() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Validates the trimmed student fields, returning the first error found or null if valid.
     * Field lengths are capped to match the students table's VARCHAR columns.
     */
    public static String validate(String firstName, String lastName, String email, String course) {
        if (isBlank(firstName) || isBlank(lastName) || isBlank(email) || isBlank(course)) {
            return "All fields are required.";
        }
        if (firstName.trim().length() > 50 || lastName.trim().length() > 50) {
            return "First and last name must be 50 characters or fewer.";
        }
        if (email.trim().length() > 100) {
            return "Email must be 100 characters or fewer.";
        }
        if (course.trim().length() > 100) {
            return "Course must be 100 characters or fewer.";
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            return "Enter a valid email address.";
        }
        return null;
    }
}
