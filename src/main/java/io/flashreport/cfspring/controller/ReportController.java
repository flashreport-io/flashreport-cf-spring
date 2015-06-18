package io.flashreport.cfspring.controller;

import io.flashreport.cfspring.service.ReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * Created by Nicolas Lejeune on 16/06/15.
 */
@RestController
@SuppressWarnings("unused")
public class ReportController {

    @Autowired
    ReportManager reportManager;

    @RequestMapping(value = "/report/new", method = {RequestMethod.GET, RequestMethod.POST})
    public String requestReportGeneration() {
        String uuid = reportManager.generateReport();

        UriComponents uriComponents = MvcUriComponentsBuilder
                .fromMethodName(ReportController.class, "viewReportStatus", uuid).build();

        return "Report request sent, check it at " + uriComponents.encode().toUri().toString();

    }

    @RequestMapping("/report/{uuid}")
    public String viewReportStatus(@PathVariable String uuid) {
        return reportManager.getReportStatus(uuid);
    }

}
