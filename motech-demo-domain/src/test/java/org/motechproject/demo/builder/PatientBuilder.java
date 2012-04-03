package org.motechproject.demo.builder;

import org.joda.time.LocalDate;
import org.motechproject.demo.domain.patient.Gender;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.testutils.UniqueMobileNumber;
import org.motechproject.util.DateUtil;

import java.util.UUID;

public class PatientBuilder {
    private Patient patient = new Patient();

    public PatientBuilder withId(String id) {
        this.patient.setId(id);
        return this;
    }

    public PatientBuilder withPatientId(String id) {
        this.patient.setPatientId(id);
        return this;
    }

    public PatientBuilder withMobileNumber(String mobileNumber) {
        patient.setMobilePhoneNumber(mobileNumber);
        return this;
    }

    public PatientBuilder withPasscode(String passcode) {
        patient.setPasscode(passcode);
        return this;
    }

    public PatientBuilder withGender(Gender gender) {
        patient.setGender(gender);
        return this;
    }
    public PatientBuilder withDateOfBirth(LocalDate dateOfBirth) {
        patient.setDateOfBirth(dateOfBirth);
        return this;
    }

    public PatientBuilder withDefaults() {
        return this.withId(UUID.randomUUID().toString()).
                withPatientId("1234_" + DateUtil.now().getMillis()).
                withMobileNumber(Long.toString(UniqueMobileNumber.generate())).
                withPasscode("1234").
                withDateOfBirth(DateUtil.newDate(1990, 5, 21)).
                withGender(Gender.Female);
    }

    public Patient build() {
        return this.patient;
    }

    public static PatientBuilder startRecording() {
        return new PatientBuilder();
    }
}
