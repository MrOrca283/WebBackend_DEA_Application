package ru.rutmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.dto.ShowDetailedExamInfoDto;
import ru.rutmiit.models.entities.Exam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, String> {

    //нужно как-то отсортировать по дате и времени!

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Exam e WHERE e.examId = :examId")
    boolean existsByExamId(@Param("examId") String examId);

    @Query("SELECT e FROM Exam e WHERE " +
            "LOWER(e.applicationId) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Exam> searchByApplicationId(@Param("searchTerm") String searchTerm);

    @Modifying
    @Transactional
    @Query("DELETE FROM Exam e WHERE e.examId = :examId")
    void deleteByExamId(@Param("examId") String examId);
}
