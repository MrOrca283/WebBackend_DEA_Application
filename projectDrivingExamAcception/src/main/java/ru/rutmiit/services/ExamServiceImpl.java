package ru.rutmiit.services;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.endpoint.Show;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.dto.AddExamDto;
import ru.rutmiit.dto.ShowDetailedCandidateInfoDto;
import ru.rutmiit.dto.ShowDetailedExamInfoDto;
import ru.rutmiit.dto.ShowDetailedMedBlankInfoDto;
import ru.rutmiit.models.entities.Candidate;
import ru.rutmiit.models.entities.Exam;
import ru.rutmiit.models.entities.MedBlank;
import ru.rutmiit.models.entities.User;
import ru.rutmiit.models.exceptions.CandidateNotFoundException;
import ru.rutmiit.models.exceptions.ExamNotFoundException;
import ru.rutmiit.models.exceptions.MedBlankNotFoundException;
import ru.rutmiit.repositories.CandidateRepository;
import ru.rutmiit.repositories.ExamRepository;
import ru.rutmiit.repositories.MedBlankRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly=true)
public class ExamServiceImpl implements ExamService
{


    private final ExamRepository examRepository;
    private final CandidateRepository candidateRepository;
    private final MedBlankRepository medBlankRepository;
    private final ModelMapper mapper;

    public ExamServiceImpl(ExamRepository examRepository, ModelMapper mapper,CandidateRepository candidateRepository, MedBlankRepository medBlankRepository)
    {
        this.examRepository = examRepository;
        this.mapper=mapper;
        this.candidateRepository=candidateRepository;
        this.medBlankRepository=medBlankRepository;
    }

    @Override
    @Transactional
/*    @CacheEvict(cacheNames = "exams", allEntries = true)*/
    public void addExam(AddExamDto examDto)
    {
        Exam exam = mapper.map(examDto, Exam.class);
        examRepository.save(exam);
    }


    @Override
    public Page <ShowDetailedExamInfoDto> allExamsPaginated(Pageable pageable) {
        return examRepository.findAll(pageable)
                .map(exam->mapper.map(exam, ShowDetailedExamInfoDto.class));
    }



    @Override
    public boolean checkCandidateAdmissionToExam (String applicationId) //для мента
    {
        //сначала проверить теорию, а потом мед данные
        Candidate candidate = candidateRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> {
                    return new CandidateNotFoundException("Не найден кандидат с таким номером заявления");
                        });

        //проверка теории, если нет - сразу false;
        if (candidate.isTheoryPassed()==false) {return false;}

        //получение и проверка мед данных
        String candidatePassport = mapper.map(candidate, ShowDetailedCandidateInfoDto.class).getPassportSeriesAndNumber();

        List<ShowDetailedMedBlankInfoDto> medBlanks = medBlankRepository.searchByPassportSerAndNum(candidatePassport).stream()
                .map(medBlank->mapper.map(medBlank,ShowDetailedMedBlankInfoDto.class))
                .collect(Collectors.toList());
        
        for (ShowDetailedMedBlankInfoDto currentMedBlank:medBlanks)
        {
            Period period = Period.between(currentMedBlank.getDateOfHealthCheck(), LocalDate.now()).normalized();
            int daysFromLastMedicalTests = period.getDays()+ period.getMonths()*30+period.getYears()*355;

            if (Objects.equals(currentMedBlank.getDrivingCategory(), candidate.getLicenceType()) && daysFromLastMedicalTests<9355 &&
                    currentMedBlank.isAcceptedOnDrivingCategory()==true)
            {return true;}
        }

        return false;
    }

    @Override
    public List<ShowDetailedExamInfoDto> searchUserExams(String passportSerAndNum)
    {

        //у юзера есть паспорт, по нему можно найти номера заявлений всех
        //а уже по ним экзамены назначенные

        List<ShowDetailedCandidateInfoDto> candidateApplications = candidateRepository.searchByPassportSeriesAndNumber(passportSerAndNum)
                .stream().map(candidate ->mapper.map(candidate, ShowDetailedCandidateInfoDto.class))
                .collect(Collectors.toList());


        List<ShowDetailedExamInfoDto> allExamsByApplicationIds = new ArrayList<>();

        for (ShowDetailedCandidateInfoDto application:candidateApplications)
        {
            allExamsByApplicationIds.addAll(examRepository.searchByApplicationId(application.getApplicationId()).stream()
                    .map(exam -> mapper.map(exam,ShowDetailedExamInfoDto.class)).toList());
        }
        return allExamsByApplicationIds;
    }



    @Override
    @Transactional
    public void deleteExamById(String examId)
    {
        if (!examRepository.existsByExamId(examId))
        {
            throw new ExamNotFoundException("Не удалось найти экзамен с ID "+examId);
        }
        examRepository.deleteByExamId(examId);
    }

}
