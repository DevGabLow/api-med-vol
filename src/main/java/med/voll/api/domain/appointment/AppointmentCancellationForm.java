package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancellationForm(
        @NotNull
        Long appointmentId,
        @NotNull
        TypeReasonCancellation reason
) {
}
