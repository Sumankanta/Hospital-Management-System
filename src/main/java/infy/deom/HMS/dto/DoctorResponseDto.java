package infy.deom.HMS.dto;

import lombok.Data;

@Data
public class DoctorResponseDto {
    private Long doctorId;
    private String name;
    private String specialization;
    private String email;
}
