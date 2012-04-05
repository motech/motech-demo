package org.motechproject.demo.builder;

import org.joda.time.LocalDate;
import org.motechproject.demo.domain.patient.Gender;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.domain.patient.Status;
import org.motechproject.demo.testutils.UniqueMobileNumber;
import org.motechproject.util.DateUtil;

import java.util.UUID;

public class PatientBuilder {
    private Patient patient = new Patient();

    public PatientBuilder withId(String id) {
        this.patient.setId(id);
        return this;
    }

    public PatientBuilder withExternalId(String id) {
        this.patient.setExternalId(id);
        return this;
    }

    public PatientBuilder withName(String name) {
        this.patient.setName(name);
        return this;
    }

    public PatientBuilder withPhoneNumber(String mobileNumber) {
        patient.setPhoneNumber(mobileNumber);
        return this;
    }

    public PatientBuilder withGender(Gender gender) {
        patient.setGender(gender);
        return this;
    }

    public PatientBuilder withStatus(Status status) {
        patient.setStatus(status);
        return this;
    }

    public PatientBuilder withDateOfBirth(LocalDate dateOfBirth) {
        patient.setDateOfBirth(dateOfBirth);
        return this;
    }

    public PatientBuilder withDefaults() {
        return this.withId(UUID.randomUUID().toString()).
                withExternalId("1234_" + DateUtil.now().getMillis()).
                withName("name").
                withPhoneNumber(Long.toString(UniqueMobileNumber.generate())).
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
