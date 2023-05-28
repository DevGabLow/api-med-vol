package med.voll.api.controller;

import med.voll.api.domain.appointment.DetailSchedule;
import med.voll.api.domain.appointment.ScheduleAppointment;
import med.voll.api.domain.appointment.ScheduleAppointmentForm;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ScheduleAppointmentForm> dataAppointmentjson;

    @Autowired
    private JacksonTester<DetailSchedule> dataDetailAppointmentjson;

    @MockBean
    private ScheduleAppointment scheduleAppointment;

    @Test
    @DisplayName("Deveria devolver código HTTP 400 quando informações estão inválidas")
    @WithMockUser
    void createScheduleScenario1() throws Exception {
       var response =  mvc.perform(post("/appointment"))
                .andReturn().getResponse();
       assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código HTTP 200 quando informações estão válidas")
    @WithMockUser
    void createScheduleSchenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var detailData = new DetailSchedule(null, 2l, 5l, date);
        when(scheduleAppointment.schedule(any())).thenReturn(detailData);
        var response =  mvc
                .perform(
                        post("/appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataAppointmentjson.write(
                                        new ScheduleAppointmentForm(2l, Specialty.CARDIOLOGIA, 5l, date)
                                ).getJson())
                )
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonwait = dataDetailAppointmentjson.write(detailData).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonwait);
    }
}