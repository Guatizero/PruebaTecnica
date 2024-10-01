package utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

//Leer archivos .csv para extrar los datos de entrada

public class CsvDataLoader {
    private String csvPath;

    public CsvDataLoader(String csvPath) {
        this.csvPath = csvPath;
    }

    public List<String> getInputDataFromCsv() throws IOException {
        List<String> data = new ArrayList<>();

        try (Reader reader = new FileReader(this.csvPath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.EXCEL.withDelimiter(',').withHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                if (isUserCredentialsRecord(csvRecord)) {
                    data.addAll(getUserCredentialsData(csvRecord));
                } else if (isPersonalInfoRecord(csvRecord)) {
                    data.addAll(getPersonalInfoData(csvRecord));
                }

                break;
            }
        } catch (IOException e) {
            System.err.println("Error con el archivo : " + e.getMessage());
            throw e;
        }

        return data;
    }

    private boolean isUserCredentialsRecord(CSVRecord record) {
        return record.isMapped("UserName") && record.isMapped("Password") &&
                record.isMapped("Category") && record.isMapped("Article");
    }

    private List<String> getUserCredentialsData(CSVRecord record) {
        List<String> data = new ArrayList<>();

        data.add(record.get("UserName"));
        data.add(record.get("Password"));
        data.add(record.get("Category"));
        data.add(record.get("Article"));

        return data;
    }

    private boolean isPersonalInfoRecord(CSVRecord record) {
        return record.isMapped("Name") && record.isMapped("Country") &&
                record.isMapped("City") && record.isMapped("Credit Card") &&
                record.isMapped("Month") && record.isMapped("Year");
    }

    private List<String> getPersonalInfoData(CSVRecord record) {
        List<String> data = new ArrayList<>();

        data.add(record.get("Name"));
        data.add(record.get("Country"));
        data.add(record.get("City"));
        data.add(record.get("Credit Card"));
        data.add(record.get("Month"));
        data.add(record.get("Year"));

        return data;
    }
}
