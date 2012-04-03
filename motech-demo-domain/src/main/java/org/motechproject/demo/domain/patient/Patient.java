package org.motechproject.demo.domain.patient;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.ektorp.support.TypeDiscriminator;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.motechproject.demo.domain.common.CouchEntity;
import org.motechproject.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@TypeDiscriminator("doc.documentType == 'Patient'")
public class Patient extends CouchEntity {

    @Getter
    @Setter
    protected String patientId;

    @Getter
    @Setter
    @NotNull
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Phone Number should be numeric and 10 digits long.")
    protected String mobilePhoneNumber;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "S-", pattern = "dd/MM/yyyy")
    @NotNull
    protected LocalDate dateOfBirth;

    @Getter
    @Setter
    @NotNull
    private Gender gender;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "S-", pattern = "dd/MM/yyyy")
    private Date registrationDate;

    @Getter
    @Setter
    @NotNull
    private String passcode;

    @JsonIgnore
    public int getAge() {
        Period period = new Period(getDateOfBirth(), DateUtil.today(), PeriodType.years());
        return period.getYears();
    }
}