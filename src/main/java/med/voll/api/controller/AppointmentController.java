package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private ScheduleAppointment scheduleService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createSchedule(@RequestBody @Valid ScheduleAppointmentForm form) {
        var appointment = scheduleService.schedule(form);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> cancel(@RequestBody @Valid AppointmentCancellationForm form){
        scheduleService.cancel(form);
        return ResponseEntity.noContent().build();
    }
}
