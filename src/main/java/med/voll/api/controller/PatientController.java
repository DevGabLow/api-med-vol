package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PatientData form, UriComponentsBuilder builder){
       Patient patient =  repository.save(new Patient(form));
       URI uri = builder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
       return ResponseEntity.created(uri).body(new PatientDetailData(patient));
    }


    @GetMapping
    public ResponseEntity<Page<PatientListData>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        Page page = repository.findAllByIsDeletedFalse(pagination).map(PatientListData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid PatientUpdateData form){
        Patient patient = repository.getReferenceById(form.id());
        patient.updateInfos(form);
        return ResponseEntity.ok(new PatientDetailData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);
        patient.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailData(patient));
    }
}
