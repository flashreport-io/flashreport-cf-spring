package io.flashreport.cfspring.integration;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Information about a report.
 *
 * @author Nicolas Lejeune
 */
@SuppressWarnings("unused")
public class ReportStatus {

    private String reportUuid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, d MMM yyyy HH:mm:ss Z")
    private Date creationRequestDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, d MMM yyyy HH:mm:ss Z")
    private Date availabilityDate;

    private ReportState state;
    private String title;
    private String payloadUrl;
    private String logUrl;
    private String downloadUrl;
    private String storageUrl;

    public String getReportUuid() {
        return reportUuid;
    }

    public Date getCreationRequestDate() {
        return creationRequestDate;
    }

    public Date getAvailabilityDate() {
        return availabilityDate;
    }

    public String getPayloadUrl() {
        return payloadUrl;
    }

    public ReportState getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getStorageUrl() {
        return storageUrl;
    }
}
