package med.voll.api.domain.appointment.validations.cancel;

import med.voll.api.domain.appointment.AppointmentCancellationForm;

public interface ValidationCancel {

    public void validation(AppointmentCancellationForm form);
}
