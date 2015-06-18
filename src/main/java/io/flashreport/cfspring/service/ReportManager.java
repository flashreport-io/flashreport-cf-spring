package io.flashreport.cfspring.service;

/**
 * Created by Nicolas Lejeune on 16/06/15.
 */
public interface ReportManager {

    /**
     * Use a flashreport bound service to request the generation of a report
     *
     * @return the UUID of the generated report
     */
    String generateReport();

    String getReportStatus(String UUID);

}
