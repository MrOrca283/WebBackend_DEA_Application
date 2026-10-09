package ru.rutmiit.services;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.dto.AddCandidateDto;
import ru.rutmiit.dto.ShowDetailedCandidateInfoDto;
import ru.rutmiit.models.entities.Candidate;
import ru.rutmiit.models.exceptions.CandidateNotFoundException;
import ru.rutmiit.repositories.CandidateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CandidateServiceImpl implements CandidateService {


    private final CandidateRepository candidateRepository;
    private final ModelMapper mapper;

    public CandidateServiceImpl(CandidateRepository candidateRepository, ModelMapper mapper)
    {
        this.candidateRepository=candidateRepository;
        this.mapper=mapper;

    }

    @Override
    @Transactional
    @CacheEvict(cacheNames="candidates",allEntries= true)

    public void addCandidate(AddCandidateDto candidateDto)
    {
        Candidate candidate = mapper.map(candidateDto, Candidate.class);
        candidateRepository.save(candidate);
    }


    @Override
    public Page<ShowDetailedCandidateInfoDto> allCandidatesPaginated(Pageable pageable)
    {
        return candidateRepository.findAll(pageable)
                .map(candidate->mapper.map(candidate,ShowDetailedCandidateInfoDto.class));
    }


    @Override
    public ShowDetailedCandidateInfoDto findByApplicationId(String applicationId)
    {
        Candidate candidate = candidateRepository.findByApplicationId(applicationId)
                .orElseThrow(()->{
                    return new CandidateNotFoundException("Заявление кандидата с такими данными не найдено");
                });
        return mapper.map(candidate,ShowDetailedCandidateInfoDto.class);

    }


    @Override
    public List<ShowDetailedCandidateInfoDto> searchByPassportSerAndNum(String passportSerAndNum)
    {
        List<ShowDetailedCandidateInfoDto> Candidates = candidateRepository.searchByPassportSeriesAndNumber(passportSerAndNum)
                .stream().map(Candidate -> mapper.map(Candidate, ShowDetailedCandidateInfoDto.class))
                .collect(Collectors.toList());

        return Candidates;
    }


    @Override
    public List<String> allApplications()
    {
        List<ShowDetailedCandidateInfoDto> allCandidates = candidateRepository.findAll()
                .stream().map(Candidate -> mapper.map(Candidate, ShowDetailedCandidateInfoDto.class))
                .collect(Collectors.toList());

        List<String> allApplicationIds=new ArrayList<>(); ;

        for (ShowDetailedCandidateInfoDto candidate: allCandidates)
        {
            allApplicationIds.add(candidate.getApplicationId());
        }
        return allApplicationIds;
    }


    @Override
    @Transactional
    public void removeCandidate(String applicationId)
    {
        if (!candidateRepository.existsByApplicationId(applicationId))
        {
            throw new CandidateNotFoundException("Не удалось найти кандидата с заявлением "+applicationId);
        }
        candidateRepository.deleteByApplicationId(applicationId);
    }


}
