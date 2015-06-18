package io.flashreport.cfspring.service;

import io.flashreport.cfspring.integration.FlashreportClient;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.util.Scanner;

/**
 * Created by Nicolas Lejeune on 16/06/15.
 */
@Service
@SuppressWarnings("unused")
public class ReportManagerImpl implements ReportManager {

    private final FlashreportClient client = new FlashreportClient();

    @Override
    public String generateReport() {
        URI reportUri = client.generateReport(readTestMessage("orders-smallest.json"));
        String[] pathElements = reportUri.getPath().split("/");

        // return the last part which contains the report UUID
        return pathElements[pathElements.length - 1];

    }

    private String readTestMessage(String fileName) {
        InputStream is = this.getClass().getResourceAsStream("/" + fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(is, "UTF-8")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                stringBuilder.append(line).append("\n");
            }
            scanner.close();
        }
        return stringBuilder.toString();
    }
}
