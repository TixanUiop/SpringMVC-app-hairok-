package org.evgeny.hairok.Controller;


import lombok.RequiredArgsConstructor;
import org.evgeny.hairok.Entity.Client;
import org.evgeny.hairok.Service.ClientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class ClientDashboardController {

    private final ClientService clientService;

    @GetMapping("/client-dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserDetails userProfile = clientService.loadUserByUsername(userDetails.getUsername());
        model.addAttribute("user", userProfile);
        return "Dashboards/client-dashboard";
    }

}
