package com.gerimedica.assignment.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.gerimedica.assignment.helper.CsvFileHelper;
import com.gerimedica.assignment.model.CsvFile;
import com.gerimedica.assignment.repository.CsvFileRepository;

@Service
public class CsvFileServiceImpl implements CsvFileService {

	@Autowired
	CsvFileRepository csvFileRepository;

	public void uploadFile(MultipartFile file) throws NumberFormatException, ParseException {
		try {
			List<CsvFile> csvFile = CsvFileHelper.convertCsvToCsvFileTable(file.getInputStream());
			csvFileRepository.saveAll(csvFile);
		} catch (IOException e) {
			throw new RuntimeException("Failed to store data in csv: " + e.getMessage());
		}
	}

	public void uploadData(List<CsvFile> csvFile) {
		try {
			csvFileRepository.saveAll(csvFile);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save data in db " + e.getMessage());
		}
	}

	public List<CsvFile> fecthAllData() {
		try {
			return csvFileRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve all data " + e.getMessage());
		}
	}

	public CsvFile fecthByCode(String code) {
		try {
			return csvFileRepository.findByCode(code);
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve data " + e.getMessage());
		}
	}

	public void deleteAllData() {
		try {
			csvFileRepository.deleteAll();
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete data " + e.getMessage());
		}
	}

	public ByteArrayInputStream downloadFile() {
		List<CsvFile> csvFile = csvFileRepository.findAll();

		ByteArrayInputStream inputStrean = CsvFileHelper.csvFileTableDataToCSV(csvFile);
		return inputStrean;
	}

}
