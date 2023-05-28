package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.ExceptionValidation;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentValidationExistAppointment implements ValidationAppointment{

    @Autowired
    private AppointmentRepository repository;

    public void validation(ScheduleAppointmentForm form){
        var existAppointment = repository.existsByDoctorIdAndDate(form.doctorId(), form.date());
        if(existAppointment){
            throw new ExceptionValidation("Invalid Appointment Date not available");
        }
    }
}
