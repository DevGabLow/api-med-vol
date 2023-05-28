package med.voll.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record ScheduleAppointmentForm(
        Long doctorId,

        Specialty specialty,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime date) {

}
