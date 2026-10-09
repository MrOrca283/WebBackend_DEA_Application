package ru.rutmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.dto.ShowDetailedCandidateInfoDto;
import ru.rutmiit.models.entities.Candidate;
import ru.rutmiit.models.entities.Exam;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, String> {

/*    @Query("SELECT c FROM Candidate c WHERE " +
            "LOWER(c.fullName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Candidate> searchByFullName(@Param("searchTerm") String searchTerm);*/

    @Query("SELECT c FROM Candidate c WHERE c.passportSeriesAndNumber = :passportSeriesAndNumber")
    List<Candidate> searchByPassportSeriesAndNumber(@Param("passportSeriesAndNumber") String passportSeriesAndNumber);

/*    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM Candidate c WHERE c.passportSeriesAndNumber = :passportSeriesAndNumber")
    boolean existsByPassportSeriesAndNumber(@Param("passportSeriesAndNumber") String passportSeriesAndNumber);*/

    @Query("SELECT c FROM Candidate c WHERE c.applicationId = :applicationId")
    Optional<Candidate> findByApplicationId(@Param("applicationId") String applicationId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM Candidate c WHERE c.applicationId = :applicationId")
    boolean existsByApplicationId(@Param("applicationId") String applicationId);


    @Modifying
    @Transactional
    @Query("DELETE FROM Candidate c WHERE c.applicationId = :applicationId")
    void deleteByApplicationId(@Param("applicationId") String applicationId);


}
