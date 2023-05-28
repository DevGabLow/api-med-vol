package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.appointment.ScheduleAppointmentForm;

public interface ValidationAppointment {
    public void validation(ScheduleAppointmentForm form);
}
