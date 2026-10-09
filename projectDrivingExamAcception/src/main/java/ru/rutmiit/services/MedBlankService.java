package ru.rutmiit.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rutmiit.dto.AddMebBlankDto;
import ru.rutmiit.dto.ShowDetailedCandidateInfoDto;
import ru.rutmiit.dto.ShowDetailedMedBlankInfoDto;

import java.time.LocalDate;
import java.util.List;

public interface MedBlankService {


    void addMedBlank(AddMebBlankDto medBlankDto);

    Page<ShowDetailedMedBlankInfoDto> allMedBlanksPaginated(Pageable pageable);

    List<ShowDetailedMedBlankInfoDto> searchByPassportSerAndNum(String passportSerAndNum);

    ShowDetailedMedBlankInfoDto findByBlankCode(String blankCode);

    void deleteMedBlankByBlankCode(String BlankCode);


}
