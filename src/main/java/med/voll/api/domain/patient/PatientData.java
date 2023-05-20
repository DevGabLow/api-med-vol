package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record PatientData(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String cellPhone,
        @NotBlank
        String cpf,
        @NotNull
        @Valid
        AddressData address
) {
}
