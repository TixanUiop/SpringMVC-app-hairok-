package org.evgeny.hairok.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class ClientDashboardController {


    @GetMapping("/client-dashboard")
    public String dashboard() {

        return "Dashboards/client-dashboard";
    }

}
