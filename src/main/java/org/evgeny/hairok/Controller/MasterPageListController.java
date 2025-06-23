package org.evgeny.hairok.Controller;


import lombok.RequiredArgsConstructor;
import org.evgeny.hairok.DTO.MasterProfilesDTO;
import org.evgeny.hairok.Service.MasterService;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/masters-page-list")
@RequiredArgsConstructor
public class MasterPageListController {

    private final MasterService masterService;

//    @GetMapping
//    public String getMasterList(Model model, @PageableDefault(size = 10) Pageable pageable) {
//
//        //List<MasterProfilesDTO> allMasterProfiles = masterService.getAllMasterProfiles();
//        //List<MasterProfilesDTO> pageAllMasterProfiles = masterService.getPageAllMasterProfiles(pageable);
//
//
//        model.addAttribute("masterProfiles", pageAllMasterProfiles);
//        return "Dashboards/clint-dashboard-master-list";
//    }




}
