package med.voll.api.domain.appointment;

import med.voll.api.domain.ExceptionValidation;
import med.voll.api.domain.appointment.validations.appointment.ValidationAppointment;
import med.voll.api.domain.appointment.validations.cancel.ValidationCancel;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAppointment {

    @Autowired
    private AppointmentRepository repository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidationAppointment> validations;

    @Autowired
    private List<ValidationCancel> validationsCancel;

    public DetailSchedule schedule(ScheduleAppointmentForm form){

        if(!patientRepository.existsById(form.patientId())){
            throw new ExceptionValidation("Invalid Patient");
        }
        if(form.doctorId() != null && !doctorRepository.existsById(form.doctorId())){
            throw new ExceptionValidation("Invalid doctor");
        }
        validations.forEach(v -> v.validation(form));

        var doctor = choiceOfRandomDoctor(form);
        if(doctor == null){
            throw new ExceptionValidation("Not exist Doctor available");
        }
        var patient = patientRepository.getReferenceById(form.patientId());

        var appointment = new Appointment(null, doctor,patient, form.date(), null);
        return new DetailSchedule( repository.save(appointment));
    }

    private Doctor choiceOfRandomDoctor(ScheduleAppointmentForm form) {
        if(form.doctorId() != null){
            return doctorRepository.getReferenceById(form.doctorId());
        }
        if(form.specialty() == null){
            throw new ExceptionValidation("Specialty required");
        }
        return doctorRepository.pickFreeRandomDoctorOnDate(form.specialty(), form.date());

    }

    public void cancel(AppointmentCancellationForm form) {
        if(!repository.existsById(form.appointmentId())){
            throw new ExceptionValidation("Invalid Appointment Id");
        }
        validationsCancel.forEach(v -> v.validation(form));
        var appointment = repository.getReferenceById(form.appointmentId());
        appointment.cancel(form.reason());
    }
}
