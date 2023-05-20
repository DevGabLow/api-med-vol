package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record PatientDetailData(Long id,String name, String email, Address address) {
    public PatientDetailData(Patient patient){
        this(patient.getId(), patient.getName(),patient.getEmail(),patient.getAddress());
    }
}
