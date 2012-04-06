package org.motechproject.demo.domain.patient;

public enum Gender {
    Male,
    Female;

    public static Gender get(String gender) {
        if ("fF".contains(gender)) return Female;
        else return Male;
    }

}
