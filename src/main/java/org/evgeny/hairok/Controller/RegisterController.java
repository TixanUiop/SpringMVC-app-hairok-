package org.evgeny.hairok.Controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.evgeny.hairok.DTO.ClientDTO;
import org.evgeny.hairok.DTO.MasterProfilesDTO;
import org.evgeny.hairok.DTO.RegisterClientDTO;
import org.evgeny.hairok.DTO.RegisterMasterAccountDTO;
import org.evgeny.hairok.Exception.PhoneExist;
import org.evgeny.hairok.Service.ClientService;
import org.evgeny.hairok.Service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
@MultipartConfig
public class RegisterController {

    private final RegisterService registerService;
    private final ClientService clientService;


    @PostMapping(value = "/master", consumes = "multipart/form-data")
    public String masterRegisterStepTwo(@Valid @ModelAttribute MasterProfilesDTO masterProfilesDTO,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                        Model model, HttpSession httpSession, @RequestParam("avatar") MultipartFile avatar) {

        if (avatar.isEmpty()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("avatarError", "Аватар обязателен");
            redirectAttributes.addFlashAttribute("registerMasterDTO", masterProfilesDTO);
            return "redirect:/register/master-personal-info";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("registerMasterDTO", masterProfilesDTO);
            return "redirect:/register/master-personal-info";
        }

        RegisterMasterAccountDTO registerMasterAccountDTO = (RegisterMasterAccountDTO) httpSession.getAttribute("account");

        try {
            registerService.saveMasterProfileAndAccount(registerMasterAccountDTO, masterProfilesDTO);
        }
        catch (PhoneExist phoneExist) {
            bindingResult.rejectValue("phone", "phoneExist", "Мастер с таким номером уже зарегистрирован");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("registerMasterDTO", masterProfilesDTO);
            return "redirect:/register/master-personal-info";
        }
        catch (RuntimeException e) {
            bindingResult.rejectValue("avatar", "error", "Не удалось загрузить фото. Попробуйте позже");
            return "redirect:/register/master-personal-info";
        }


        redirectAttributes.addFlashAttribute("thanksForRegistration", "Благодарим за регистрацию");
        return "redirect:/login/loginMaster";
    }

    @GetMapping("/master-personal-info")
    public String masterRegisterStepTwo(Model model, @ModelAttribute MasterProfilesDTO masterProfilesDTO) {


        if (!model.containsAttribute("registerMasterDTO")) {
            model.addAttribute("registerMasterDTO", new MasterProfilesDTO());
        }
        model.addAttribute("registerMasterDTO", model.getAttribute("registerMasterDTO"));
        return "/Register/master-register-personal-info";
    }

    @GetMapping("/master")
    public String masterRegisterStepOne(@ModelAttribute RegisterMasterAccountDTO registerMasterAccountDTO,
                                 Model model) {
        model.addAttribute("registerClientDTO", registerMasterAccountDTO);
        return "/Register/master-register";
    }


    @PostMapping(value = "/master")
    public String masterRegisterStepOne(@Valid @ModelAttribute("account") RegisterMasterAccountDTO masterAccountDTO,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                        Model model, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("registerMasterDTO", masterAccountDTO);
            return "redirect:/register/master";
        }

        if (registerService.checkEmail(masterAccountDTO.getEmail())) {
            bindingResult.rejectValue("email", "emailExist", "Мастер с таким Email уже зарегистрирован");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("RegisterMasterAccountDTO", masterAccountDTO);
            return "redirect:/register/master";
        }

        httpSession.setAttribute("account", masterAccountDTO);

        model.addAttribute("registerMasterDTO", new MasterProfilesDTO());
        return "/Register/master-register-personal-info";
    }


    @PostMapping()
    public String clientRegister(@Valid @ModelAttribute RegisterClientDTO registerClientDTO,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("registerClientDTO", registerClientDTO);
            return "redirect:/register";
        }

        try {
            Optional<ClientDTO> save = registerService.save(registerClientDTO);
        }
        catch (PhoneExist e) {
            bindingResult.rejectValue("phone", "phoneExist", "Пользователь с таким номером уже зарегистрирован");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("registerClientDTO", registerClientDTO);
            return "redirect:/register";
        }

        return "/Register/client-register";
    }

    @GetMapping()
    public String clientRegister(@ModelAttribute RegisterClientDTO registerClientDTO,
                                 Model model) {

        model.addAttribute("registerClientDTO", registerClientDTO);
        return "/Register/client-register";
    }
}
