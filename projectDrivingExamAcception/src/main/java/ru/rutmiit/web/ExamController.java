package ru.rutmiit.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.AddExamDto;
import ru.rutmiit.dto.ShowDetailedExamInfoDto;
import ru.rutmiit.models.entities.User;
import ru.rutmiit.services.AuthService;
import ru.rutmiit.services.CandidateService;
import ru.rutmiit.services.ExamService;

import java.security.Principal;
import java.time.LocalDateTime;

//log.info
@Slf4j
@Controller
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;
    private final CandidateService candidateService;
    private final AuthService authService;

    public ExamController(ExamService examService, CandidateService candidateService, AuthService authService)
    {
        this.candidateService=candidateService;
        this.authService=authService;
        this.examService=examService;
    }

    @GetMapping("/add")
    public String addExam(Model model)
    {
        model.addAttribute("availableApplications", candidateService.allApplications());
        return "exam-add";
    }

    @ModelAttribute("examModel")
    public AddExamDto initExam() {return new AddExamDto();}

    @PostMapping("/add") //нужны те самая проверка из сервиса
    public String addExam(@Valid AddExamDto examModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Principal principal)
    {
        if (bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("examModel",examModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.examModel",
                    bindingResult
            );

            return "redirect:/exams/add";

        }


        if (examService.checkCandidateAdmissionToExam(examModel.getApplicationId())==true)
        {
            String username = principal.getName();

            // 2. Ищем полную сущность User по username, чтобы получить id
            User user = authService.getUser(username);


            examModel.setCreatedAt(LocalDateTime.now());
            examModel.setExamSetBy(user.getUserId());
            examService.addExam(examModel);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Экзамен по заявлению" + examModel.getApplicationId() + "успешно назначен");
            return "redirect:/exams/all";
        }
        else
        {
            redirectAttributes.addFlashAttribute("examModel",examModel);
            redirectAttributes.addFlashAttribute("unsuccessMessage",
                    "Кандидат не может быть допущен до экзамена");
            return "redirect:/exams/add";
        }

    }

    @GetMapping("/all")
    public String showAllExams(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(required = false) String search,
            Model model)
    {
        //if (search != null && !search.trim().isEmpty())
       //{
            //model.addAttribute("examInfos",examService.(search));
            //model.addAttribute("search",search);
        //}
        //else
        //{
            Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy).ascending());
            Page<ShowDetailedExamInfoDto> examPage = examService.allExamsPaginated(pageable);

            model.addAttribute("examInfos", examPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", examPage.getTotalPages());
            model.addAttribute("totalItems", examPage.getTotalElements());

        //}
        return "exam-all";
    }

    @GetMapping("/exam-delete/{exam-id}")
    public String deleteExam(@PathVariable("exam-id") String examId,
                                RedirectAttributes redirectAttributes) {
        examService.deleteExamById(examId);
        redirectAttributes.addFlashAttribute("successMessage",
                "Экзамен с Id '" + examId + "' успешно удален!");
        return "redirect:/exams/all";
    }




}
