package infy.deom.HMS;

import infy.deom.HMS.dto.BloodGroupCountResponseDTO;
import infy.deom.HMS.entity.Appointment;
import infy.deom.HMS.entity.Patient;
import infy.deom.HMS.entity.Type.BloodGroupType;
import infy.deom.HMS.repository.PatientRepository;
import infy.deom.HMS.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        List<Patient> patients = patientRepository.findAllPatientWithAppointment();
        patients.forEach(p ->
                System.out.println(
                        "Patient{id=" + p.getPatientId() +
                                ", name=" + p.getName() +
                                ", email=" + p.getEmail() +
                                ", bloodGroup=" + p.getBloodGroup() +
                                ", appointment id=" + p.getAppointments().stream()
                                .findFirst()
                                .map(Appointment::getAppointmentId)
                                .orElse(null) +
                                "}"
                )
        );


//        Optional<Patient> patients = patientRepository.findByName("Suman");
//        System.out.println(patients);

//        Optional<Patient> byBirthDateOrEmail = patientRepository.findByBirthDateAndEmail(LocalDate.of(1999, 5, 22), "priya@example.com");
//        System.out.println(byBirthDateOrEmail);

//        List<Patient> byNameContaining = patientRepository.findByNameContaining("ma");
//        System.out.println(byNameContaining);

//        List<Patient> byBloodGroup = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);

//        List<Patient> byBornAfterDate = patientRepository.findByBornAfterDate(LocalDate.of(1997, 5, 2));
//
//        for (Patient patient : byBornAfterDate){
//            System.out.println(patient);
//        }

//        List<Object[]> bloodGroupType= patientRepository.countEachBloodGroupType();
//
//        for (Object[] objects : bloodGroupType){
//            System.out.println(objects[0] + " " + objects[1]);
//        }

//        List<Patient> allPatients = patientRepository.findAllPatients();
//        Page<Patient> allPatients = patientRepository.findAll(PageRequest.of(0, 4));
//        for (Patient patient : allPatients){
//            System.out.println(patient);
//        }

//        int updateNameById = patientRepository.updateNameById("SumanKanta Padhan", 1L);
//        System.out.println(updateNameById);

//        List<BloodGroupCountResponseDTO> bloodGroupType= patientRepository.countEachBloodGroupType();
//
//        for (BloodGroupCountResponseDTO bloodGroupCountResponseDTO : bloodGroupType){
//            System.out.println(bloodGroupCountResponseDTO);
//        }
    }

    @Test
    public void testTransactionMethods(){
//        Patient byId =  patientService.getPatientById(1L);
//        System.out.println(byId);

        Page<Patient> patientList = patientRepository.findAllPatients(PageRequest.of(1, 2, Sort.by("name")));

        for(Patient patient: patientList){
            System.out.println(patient);
        }
    }
}
