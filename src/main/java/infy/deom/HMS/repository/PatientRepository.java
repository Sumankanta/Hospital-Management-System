package infy.deom.HMS.repository;

import infy.deom.HMS.dto.BloodGroupCountResponseDTO;
import infy.deom.HMS.entity.Patient;
import infy.deom.HMS.entity.Type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByName(String name);
    Optional<Patient> findByBirthDateAndEmail(LocalDate birthDate, String email);
    List<Patient> findByNameContaining(String query);

    @Query("Select p from Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroupType);

    @Query("Select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("SELECT new infy.deom.HMS.dto.BloodGroupCountResponseDTO(p.bloodGroup, COUNT(p)) FROM Patient p GROUP BY p.bloodGroup")
//    List<Object[]> countEachBloodGroupType();
    List<BloodGroupCountResponseDTO> countEachBloodGroupType();

    @Query(value = "Select * from Patient", nativeQuery = true)
//    List<Patient> findAllPatients();
    Page<Patient> findAll(Pageable pageable);

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "Update Patient p set p.name = :name where p.id = :id")
    int updateNameById(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientWithAppointment();

}
