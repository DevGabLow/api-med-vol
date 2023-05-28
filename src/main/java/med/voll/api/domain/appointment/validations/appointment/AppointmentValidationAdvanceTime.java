package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.ExceptionValidation;
import med.voll.api.domain.appointment.ScheduleAppointmentForm;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentValidationAdvanceTime implements ValidationAppointment{
    public void validation(ScheduleAppointmentForm form){
        var dateAppointment = form.date();
        var now = LocalDateTime.now();
        var differenceMinuts = Duration.between(now, dateAppointment).toMinutes();

        if(differenceMinuts < 30){
            throw new ExceptionValidation("Invalid Appointment Advanced Time");
        }
    }
}
