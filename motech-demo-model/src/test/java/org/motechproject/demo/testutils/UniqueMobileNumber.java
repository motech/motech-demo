package org.motechproject.demo.testutils;

public class UniqueMobileNumber {
    public static long generate() {
        return (int) Math.pow(10, 9) + (int) (Math.random() * (int) Math.pow(10, 9));
    }
}
