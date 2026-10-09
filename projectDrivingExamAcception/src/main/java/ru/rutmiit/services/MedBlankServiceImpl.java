package ru.rutmiit.services;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.endpoint.Show;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.dto.AddMebBlankDto;
import ru.rutmiit.dto.ShowDetailedCandidateInfoDto;
import ru.rutmiit.dto.ShowDetailedMedBlankInfoDto;
import ru.rutmiit.models.entities.Candidate;
import ru.rutmiit.models.entities.MedBlank;
import ru.rutmiit.models.exceptions.CandidateNotFoundException;
import ru.rutmiit.models.exceptions.MedBlankNotFoundException;
import ru.rutmiit.repositories.MedBlankRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly=true)
public class MedBlankServiceImpl implements MedBlankService{

    private final MedBlankRepository medBlankRepository;
    private final ModelMapper mapper;

    public MedBlankServiceImpl(MedBlankRepository medBlankRepository, ModelMapper mapper)
    {
        this.medBlankRepository = medBlankRepository;
        this.mapper=mapper;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "medBlanks", allEntries = true)
    public void addMedBlank(AddMebBlankDto medBlankDto)
    {
        MedBlank medBlank = mapper.map(medBlankDto, MedBlank.class);
        medBlankRepository.save(medBlank);
    }


    @Override
    public Page<ShowDetailedMedBlankInfoDto> allMedBlanksPaginated(Pageable pageable)
    {
        return medBlankRepository.findAll(pageable)
                .map(MedBlank->mapper.map(MedBlank,ShowDetailedMedBlankInfoDto.class));
    }



    @Override
    public List<ShowDetailedMedBlankInfoDto> searchByPassportSerAndNum(String candidatePassportSerAndNum)
    {
        List<ShowDetailedMedBlankInfoDto> medBlanks = medBlankRepository.searchByPassportSerAndNum(candidatePassportSerAndNum).stream()
                .map(medBlank->mapper.map(medBlank,ShowDetailedMedBlankInfoDto.class))
                .collect(Collectors.toList());

        return medBlanks;
    }


/*
  @Override
    @Cacheable(value = "MedBlank", key = "#MedBlankPassportSerAndNum", unless = "#result == null")
    public ShowDetailedMedBlankInfoDto MedBlankDetails(String passportSerAndNum)
    {
        MedBlank medBlank = medBlankRepository.findByPassportSerAndNum(passportSerAndNum)
                .orElseThrow(()->{
                    return new MedBlankNotFoundException("Кандидат с такими данными не найден");
                });
        return mapper.map(MedBlank,ShowDetailedMedBlankInfoDto.class);
    }
    */

    @Override
    public ShowDetailedMedBlankInfoDto findByBlankCode(String blankCode)
    {
        MedBlank medBlank = medBlankRepository.findByBlankCode(blankCode)
                .orElseThrow(()->{
                    return new MedBlankNotFoundException("Заявление кандидата с такими данными не найдено");
                });
        return mapper.map(medBlank, ShowDetailedMedBlankInfoDto.class);

    }

    @Override
    @Transactional
    public void deleteMedBlankByBlankCode(String blankCode)
    {
        if (!medBlankRepository.existsByBlankCode(blankCode))
        {
            throw new MedBlankNotFoundException("Не удалось найти медбланк кандидата с кодом "+blankCode);
        }
        medBlankRepository.deleteByPassportSerAndNum(blankCode);
    }



}
