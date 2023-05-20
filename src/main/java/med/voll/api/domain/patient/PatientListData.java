package med.voll.api.domain.patient;

public record PatientListData(
        String name,
        String email,
        String cpf) {
    public PatientListData(Patient patient){
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
