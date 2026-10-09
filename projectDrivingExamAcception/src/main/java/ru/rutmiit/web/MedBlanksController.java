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
import ru.rutmiit.dto.AddMebBlankDto;
import ru.rutmiit.dto.ShowDetailedMedBlankInfoDto;
import ru.rutmiit.services.MedBlankService;

import java.time.LocalDate;


@Slf4j
@Controller
@RequestMapping("/medBlanks")
public class MedBlanksController {

    private final MedBlankService medBlankService;

    public MedBlanksController(MedBlankService medBlankService)
    {
        this.medBlankService=medBlankService;
    }

    @GetMapping("/add")
    public String addMedBlank()
    {
        return "medBlank-add";
    }

    @ModelAttribute("medBlankModel")
    public AddMebBlankDto initMedBlank() {return new AddMebBlankDto();}

    @PostMapping("/add")
    public String addMedBlank(@Valid AddMebBlankDto medBlankModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("medBlankModel",medBlankModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.medBlankModel");

            return "redirect: /medBlanks/add";

        }

        medBlankService.addMedBlank(medBlankModel);
        redirectAttributes.addFlashAttribute("successMessage",
                "Медбланк с кодом"+medBlankModel.getBlankCode() + "успешно добавлен");
        return "redirect:/medBlanks/all";

    }

    @GetMapping("/all")
    public String showAllMedBlanks(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateOfHealthCheck") String sortBy,
            @RequestParam(required = false) String search,
            Model model)
    {
        if (search != null && !search.trim().isEmpty())
        {
            model.addAttribute("medBlankInfos",medBlankService.searchByPassportSerAndNum(search));
            model.addAttribute("search",search);
        }
        else
        {
            Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy).ascending());
            Page<ShowDetailedMedBlankInfoDto> medBlankPage = medBlankService.allMedBlanksPaginated(pageable);

            model.addAttribute("medBlankInfos", medBlankPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", medBlankPage.getTotalPages());
            model.addAttribute("totalItems", medBlankPage.getTotalElements());



        }
        return "medBlank-all";
    }

    @GetMapping("/medBlank-delete/{medBlank-code}")
    public String deleteMedBlank(@PathVariable("medBlank-code") String blankCode,
                             RedirectAttributes redirectAttributes) {
        medBlankService.deleteMedBlankByBlankCode(blankCode);
        redirectAttributes.addFlashAttribute("successMessage",
                "Мед бланк'" + blankCode + "' успешно удален!");
        return "redirect:/medBlanks/all";
    }



}
