package org.motechproject.demo.domain.patient;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.ektorp.support.TypeDiscriminator;
import org.joda.time.DateTime;
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

@TypeDiscriminator("doc.documentType == 'Patient'")
public class Patient extends CouchEntity {

    @Getter
    @Setter
    @NotNull
    protected String externalId;

    @Getter
    @Setter
    @NotNull
    protected String name;

    @Getter
    @Setter
    @NotNull
    private Gender gender;


    @Getter
    @Setter
    @NotNull
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Phone Number should be numeric and 10 digits long.")
    protected String phoneNumber;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "S-", pattern = "dd/MM/yyyy")
    @NotNull
    protected LocalDate dateOfBirth;

    @Getter
    @Setter
    private Status status;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "S-", pattern = "dd/MM/yyyy HH:mm")
    private DateTime dateOpened;


    @JsonIgnore
    public int getAge() {
        Period period = new Period(getDateOfBirth(), DateUtil.today(), PeriodType.years());
        return period.getYears();
    }
}