package org.evgeny.hairok.Controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.evgeny.hairok.Service.ClientService;
import org.evgeny.hairok.Service.MasterService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpRequest;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final MasterService masterService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String clientLoginPage(@RequestParam(name = "error", required = false) String error,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверный номер телефона или пароль");
        }

        return "/Login/client-login";
    }



    @PostMapping("/loginMaster")
    public String masterAuthLoginPage(@RequestParam String email, @RequestParam String password,
                                      HttpServletRequest request) {

        UserDetails userDetails = masterService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            return "redirect:/login/loginMaster?error";
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        request.getSession().setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return "redirect:/dashboard/client-dashboard";
    }


    @GetMapping("/loginMaster")
    public String masterLoginPage(@RequestParam(name = "error", required = false) String error,
                                  Model model, RedirectAttributes redirectAttributes) {
        if (error != null) {
            model.addAttribute("thanksForRegistration", redirectAttributes.getAttribute("thanksForRegistration"));
            model.addAttribute("error", "Неверная почта или пароль");
        }
        return "/Login/master-login";
    }
}
