package io.flashreport.cfspring.service;

import io.flashreport.cfspring.integration.ReportStatus;

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

    /**
     * Get information about a report identified by its uuid
     *
     * @param uuid unique identifier of the report
     */
    ReportStatus getReportStatus(String uuid);

}
