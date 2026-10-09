package ru.rutmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.dto.ShowDetailedExamInfoDto;
import ru.rutmiit.dto.ShowDetailedMedBlankInfoDto;
import ru.rutmiit.models.entities.Candidate;
import ru.rutmiit.models.entities.MedBlank;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedBlankRepository extends JpaRepository<MedBlank, String> {


    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END " +
            "FROM MedBlank m WHERE m.blankCode = :blankCode")
    boolean existsByBlankCode(@Param("blankCode") String blankCode);

    @Query("SELECT m FROM MedBlank m " +
            "WHERE m.passportSeriesAndNumber = :passport")
    List<MedBlank> searchByPassportSerAndNum(@Param("passport") String passportSerAndNum);

    @Query("SELECT m FROM MedBlank m " +
            "WHERE m.blankCode = :blankCode")
    Optional<MedBlank> findByBlankCode(@Param("blankCode") String blankCode);


    @Modifying
    @Transactional
    @Query("DELETE FROM MedBlank m WHERE m.passportSeriesAndNumber = :passport")
    void deleteByPassportSerAndNum(@Param("passport") String passportSerAndNum);
}
//поиск по ключу должен быть!