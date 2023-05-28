package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.ExceptionValidation;
import med.voll.api.domain.appointment.ScheduleAppointmentForm;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class AppointmentValidationOpeningHours implements ValidationAppointment{

    public void validation(ScheduleAppointmentForm form){
        var dateAppointment = form.date();

        var isSunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = form.date().getHour() < 7;
        var afterClosing = form.date().getHour() > 18;

        if(isSunday || beforeOpening || afterClosing){
            throw new ExceptionValidation("Invalid Appointment");
        }
    }
}
