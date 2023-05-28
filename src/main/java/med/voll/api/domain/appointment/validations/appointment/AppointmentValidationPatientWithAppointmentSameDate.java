package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.ExceptionValidation;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentValidationPatientWithAppointmentSameDate implements ValidationAppointment{

    @Autowired
    private AppointmentRepository repository;

    public void validation(ScheduleAppointmentForm form){
        var firstTime = form.date().withHour(7);
        var lastTime = form.date().withHour(18);

        var patientSameDate = repository.existsByPatientIdAndDateBetween(form.patientId(), firstTime, lastTime);

        if(patientSameDate){
            throw new ExceptionValidation("Appointment Invalid Patient with appointment same day");
        }
    }
}
