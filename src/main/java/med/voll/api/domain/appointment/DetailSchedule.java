package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DetailSchedule(Long id, Long doctorId, Long patientId, LocalDateTime date) {
    public DetailSchedule(Appointment appointment) {
        this(appointment.getId(),appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}
