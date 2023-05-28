package med.voll.api.domain.appointment.validations.cancel;

import med.voll.api.domain.ExceptionValidation;
import med.voll.api.domain.appointment.AppointmentCancellationForm;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentValidationCancel implements ValidationCancel {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validation(AppointmentCancellationForm form) {
        var appointment = repository.getReferenceById(form.appointmentId());
        var now = LocalDateTime.now();
        var differenceBetween = Duration.between(now, appointment.getDate()).toHours();

        if(differenceBetween < 24){
            throw new ExceptionValidation("INVALID CANCEL ONLY BEFORE 24 HOURS");
        }
    }
}
