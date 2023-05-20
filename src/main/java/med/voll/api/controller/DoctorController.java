package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorListData;
import med.voll.api.domain.doctor.DoctorRepository;
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
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid DoctorData form, UriComponentsBuilder builder) {
        Doctor doctor = repository.save(new Doctor(form));
        URI uri = builder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailData(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListData>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByIsDeletedFalse(pagination).map(DoctorListData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailData> detail(@PathVariable Long id){
        Doctor doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailData(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid DoctorUpdateData form){
        var doctor =  repository.getReferenceById(form.id());
        doctor.updateInfos(form);
        return ResponseEntity.ok(new DoctorDetailData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){
        var doctor =  repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }
}
