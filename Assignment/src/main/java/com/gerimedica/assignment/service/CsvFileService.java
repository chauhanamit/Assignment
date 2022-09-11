package com.gerimedica.assignment.service;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.assignment.model.CsvFile;

public interface CsvFileService {

	public void uploadFile(MultipartFile file) throws NumberFormatException, ParseException;

	public void uploadData(List<CsvFile> csvFile);

	public List<CsvFile> fecthAllData();

	public CsvFile fecthByCode(String code);

	public void deleteAllData();

	public ByteArrayInputStream downloadFile();
}
