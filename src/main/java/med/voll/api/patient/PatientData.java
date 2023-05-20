package med.voll.api.patient;

import med.voll.api.address.AddressData;

public record PatientData(
        String name,
        String email,
        String cellPhone,
        String cpf,
        AddressData address
) {
}
