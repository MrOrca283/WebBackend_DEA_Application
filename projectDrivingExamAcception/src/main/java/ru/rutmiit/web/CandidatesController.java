package ru.rutmiit.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.AddCandidateDto;
import ru.rutmiit.dto.ShowDetailedCandidateInfoDto;
import ru.rutmiit.services.CandidateService;

@Slf4j
@Controller
@RequestMapping("/candidates")
public class CandidatesController {

    private final CandidateService candidateService;

    public CandidatesController(CandidateService candidateService)
    {
        this.candidateService=candidateService;
    }

    @ModelAttribute("candidateModel")
    public AddCandidateDto initCandidate() {
        return new AddCandidateDto();
    }

    @GetMapping("/add")
    public String addCandidate()
    {
        return "candidate-add";
    }


    @PostMapping("/add")
    public String addCandidate(@Valid AddCandidateDto candidateModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("candidateModel", candidateModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.candidateModel",
                    bindingResult);
            return "redirect:/candidates/add";
        }

        candidateService.addCandidate(candidateModel);
        redirectAttributes.addFlashAttribute("successMessage",
                "Кандидат '" + candidateModel.getFullName() + "' успешно добавлен(а)!");

        return "redirect:/candidates/all";
        
        
    }

    @GetMapping("/all")
    public String showAllCandidates( @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "fullName") String sortBy,
                                     @RequestParam(required = false) String search,
                                     Model model) {


        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("candidateInfos", candidateService.searchByPassportSerAndNum(search));
            model.addAttribute("search", search);
        } else {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            Page<ShowDetailedCandidateInfoDto> candidatePage = candidateService.allCandidatesPaginated(pageable);

            model.addAttribute("candidateInfos", candidatePage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", candidatePage.getTotalPages());
            model.addAttribute("totalItems", candidatePage.getTotalElements());
        }

        return "candidate-all";
    }


    @GetMapping("/candidate-delete/{candidate-name}")
    public String deleteCandidate(@PathVariable("candidate-name") String passportSerAndNum,
                                RedirectAttributes redirectAttributes) {
        candidateService.removeCandidate(passportSerAndNum);
        redirectAttributes.addFlashAttribute("successMessage",
                "Кандидат с данными'" + passportSerAndNum + "' успешно удален!");
        return "redirect:/candidates/all";
    }
}
