package ru.rutmiit.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rutmiit.dto.AddExamDto;
import ru.rutmiit.dto.ShowDetailedExamInfoDto;
import ru.rutmiit.models.entities.Candidate;

import java.time.LocalDate;
import java.util.List;

public interface ExamService {

    boolean checkCandidateAdmissionToExam(String applicationId);

    void addExam(AddExamDto examDto);

    Page<ShowDetailedExamInfoDto> allExamsPaginated(Pageable pageable);

    List<ShowDetailedExamInfoDto> searchUserExams (String passportSerAndNum);

    void deleteExamById(String ExamId);
}
