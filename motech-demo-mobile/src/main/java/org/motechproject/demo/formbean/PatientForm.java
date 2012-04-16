package org.motechproject.demo.formbean;

import lombok.Data;
import org.motechproject.mobileforms.api.domain.FormBean;
import org.motechproject.mobileforms.api.validator.annotations.MaxLength;
import org.motechproject.mobileforms.api.validator.annotations.RegEx;
import org.motechproject.mobileforms.api.validator.annotations.Required;

import java.util.Date;

@Data
public class PatientForm extends FormBean {
    public static final String MOTECH_ID_PATTERN = "[0-9]{4}";
    public static final String NAME_PATTERN = "[0-9.\\-\\s]*[a-zA-Z]?[a-zA-Z0-9.\\-\\s]*";
    public static final String PHONE_NO_PATTERN = "0[0-9]{9}";
    public static final String GENDER_PATTERN = "[MmFf]";

    @RegEx(pattern = MOTECH_ID_PATTERN)
    private String externalId;

    @Required
    @MaxLength(size = 50)
    @RegEx(pattern = NAME_PATTERN)
    private String name;

    @Required
    private Date dateOfBirth;

    @RegEx(pattern = GENDER_PATTERN)
    private String gender;

    @RegEx(pattern = PHONE_NO_PATTERN)
    private String phoneNumber;
}
