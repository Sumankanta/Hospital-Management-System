package infy.deom.HMS.service;

import infy.deom.HMS.entity.Insurance;
import infy.deom.HMS.entity.Patient;
import infy.deom.HMS.repository.InsuranceRepository;
import infy.deom.HMS.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){

        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient Not Found with id : " + patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient); // Bidirectional consistency maintenance

        return patient;
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient Not Found with id : " + patientId));
        patient.setInsurance(null);
        return patient;
    }
}
