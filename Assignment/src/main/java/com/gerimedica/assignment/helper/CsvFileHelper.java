package com.gerimedica.assignment.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

import com.gerimedica.assignment.model.CsvFile;

public class CsvFileHelper {

	public static List<CsvFile> convertCsvToCsvFileTable(InputStream is) throws NumberFormatException, ParseException {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			List<CsvFile> csvFileList = new ArrayList<CsvFile>();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			for (CSVRecord csvRecord : csvRecords) {
				CsvFile csvFile = new CsvFile(csvRecord.get("source"), csvRecord.get("codeListCode"),
						csvRecord.get("code"), csvRecord.get("displayValue"), csvRecord.get("longDescription"),
						csvRecord.get("fromDate"), csvRecord.get("toDate"), csvRecord.get("sortingPriority"));

				csvFileList.add(csvFile);
			}

			return csvFileList;
		} catch (IOException e) {
			throw new RuntimeException("failed to parse csv file " + e.getMessage());
		}
	}

	public static ByteArrayInputStream csvFileTableDataToCSV(List<CsvFile> csvFileList) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (CsvFile csvFile : csvFileList) {
				List<String> data = Arrays.asList(csvFile.getSource(), csvFile.getCodeListCode(), csvFile.getCode(),
						csvFile.getDisplayValue(), csvFile.getLongDescription(), csvFile.getFromDate(),
						csvFile.getToDate(), csvFile.getSortingPriority());

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}
}