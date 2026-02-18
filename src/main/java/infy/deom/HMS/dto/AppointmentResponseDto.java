package infy.deom.HMS.dto;

import infy.deom.HMS.entity.Doctor;
import infy.deom.HMS.entity.Patient;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponseDto {

    private Long appointmentId;
    private LocalDateTime appointmentTime;
    private String reason;
//    private Patient patient;
    private Doctor doctor;
}
