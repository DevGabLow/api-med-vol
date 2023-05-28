package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.ExceptionValidation;
import med.voll.api.domain.appointment.ScheduleAppointmentForm;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentValidationPatientIsDeleted implements ValidationAppointment {

    @Autowired
    private PatientRepository repository;

    public void validation(ScheduleAppointmentForm form) {
        if (form.patientId() == null) {
            return;
        }

        var patientIsDeleted = repository.findIsDeletedById(form.patientId());
        if (patientIsDeleted) {
            throw new ExceptionValidation("Invalid Appointment Patient is deactivated");
        }
    }
}
