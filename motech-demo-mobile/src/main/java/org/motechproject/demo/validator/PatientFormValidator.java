package org.motechproject.demo.validator;

import org.motechproject.demo.formbean.PatientForm;
import org.motechproject.mobileforms.api.domain.FormError;
import org.motechproject.mobileforms.api.validator.FormValidator;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PatientFormValidator extends FormValidator<PatientForm> {

    @Override
    public List<FormError> validate(PatientForm formBean) {
        return Collections.emptyList();
    }
}
