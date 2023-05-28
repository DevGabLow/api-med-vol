package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.ExceptionValidation;
import med.voll.api.domain.appointment.ScheduleAppointmentForm;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentValidationDoctorIsDeleted implements ValidationAppointment {

    @Autowired
    private DoctorRepository repository;

    public void validation(ScheduleAppointmentForm form){
        if(form.doctorId() == null){
            return;
        }

        var doctorIsDeleted = repository.findIsDeletedById(form.doctorId());
        if(doctorIsDeleted){
            throw new ExceptionValidation("Invalid Appointment Doctor is deactivated");
        }
    }
}
