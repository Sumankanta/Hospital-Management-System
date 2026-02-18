package infy.deom.HMS;

import infy.deom.HMS.entity.Appointment;
import infy.deom.HMS.entity.Insurance;
import infy.deom.HMS.entity.Patient;
import infy.deom.HMS.service.AppointmentService;
import infy.deom.HMS.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder()
                .policyNumber("SBI_2405")
                .provider("SBI")
                .validUntil(LocalDate.of(2030, 12, 31))
                .build();
        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(
                "Patient{id=" + patient.getPatientId() +
                        ", name=" + patient.getName() +
                        ", insurancePolicy=" + patient.getInsurance().getPolicyNumber() +
                        "}"
        );

        Patient disassociate = insuranceService.disaccociateInsuranceFromPatient(patient.getPatientId());
        System.out.println(
                "Patient{id=" + disassociate.getPatientId() +
                        ", name=" + disassociate.getName() +
                        ", insurancePolicy=" + (disassociate.getInsurance() != null ? disassociate.getInsurance().getPolicyNumber() : "Null") +
                        "}"
        );

    }

//    @Test
//    public void testAppointment(){
//        Appointment appointment = Appointment.builder()
//                .appointmentTime(LocalDateTime.of(2026, 1, 5, 10, 30))
//                .reason("Cancer")
//                .build();
//
//       var newAppointment = appointmentService.createNewAppointment(appointment, 1L, 3L);
//        System.out.println("Appointment {id=" + newAppointment.getAppointmentId() +
//                ", time=" + newAppointment.getAppointmentTime() +
//                ", doctor=" + newAppointment.getDoctor().getDoctorId() +
//                ", reason=" + newAppointment.getReason() +
//                "}");
//
//        var updatedAppointment = appointmentService.reassignDoctor(newAppointment.getAppointmentId(), 2L);
//        System.out.println("Appointment {id=" + updatedAppointment.getAppointmentId() +
//                ", doctor=" + updatedAppointment.getDoctor().getDoctorId() +
//                ", time=" + updatedAppointment.getAppointmentTime() +
//                ", reason=" + updatedAppointment.getReason() +
//                "}");
//    }
}
