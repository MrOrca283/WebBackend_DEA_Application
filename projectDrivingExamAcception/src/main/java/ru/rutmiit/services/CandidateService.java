package ru.rutmiit.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rutmiit.dto.AddCandidateDto;
import ru.rutmiit.dto.ShowDetailedCandidateInfoDto;
import ru.rutmiit.models.entities.Candidate;

import java.util.List;


public interface CandidateService {

    void addCandidate(AddCandidateDto candidateDto);

    List<String> allApplications();


    Page<ShowDetailedCandidateInfoDto> allCandidatesPaginated(Pageable pageable);

    List<ShowDetailedCandidateInfoDto>  searchByPassportSerAndNum(String passportSerAndNum);

    ShowDetailedCandidateInfoDto findByApplicationId(String applicationId);

    void removeCandidate(String passportSerAndNum);

}
