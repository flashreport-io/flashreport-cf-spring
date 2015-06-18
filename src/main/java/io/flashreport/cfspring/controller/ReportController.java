package io.flashreport.cfspring.controller;

import io.flashreport.cfspring.integration.ReportStatus;
import io.flashreport.cfspring.service.ReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * Created by Nicolas Lejeune on 16/06/15.
 */
@Controller
@SuppressWarnings("unused")
public class ReportController {

    @Autowired
    ReportManager reportManager;

    @RequestMapping(value = "/report/new", method = {RequestMethod.GET, RequestMethod.POST})
    public String requestReportGeneration(Model model) {
        String uuid = reportManager.generateReport();

        UriComponents uriComponents = MvcUriComponentsBuilder
                .fromMethodName(ReportController.class, "viewReportStatus", uuid).build();

        model.addAttribute("REPORT_UUID", uuid);
        model.addAttribute("REPORT_STATUS_URL", uriComponents.encode().toUri().toString());
        model.addAttribute("REPORT_DOWNLOAD_URL", uriComponents.encode().toUri().toString() + "/download");

        return "created";
    }

    @RequestMapping("/report/{uuid}")
    @ResponseBody
    public ReportStatus viewReportStatus(@PathVariable String uuid) {
        return reportManager.getReportStatus(uuid);
    }

}
