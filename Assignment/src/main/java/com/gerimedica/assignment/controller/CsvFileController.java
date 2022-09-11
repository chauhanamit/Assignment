package com.gerimedica.assignment.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.gerimedica.assignment.model.CsvFile;
import com.gerimedica.assignment.service.CsvFileService;

@RestController
@RequestMapping("/api")
public class CsvFileController {

	@Autowired
	CsvFileService csvFileService;

	@PostMapping("/file/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			csvFileService.uploadFile(file);
			return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
		} catch (Exception e) {
			return ResponseEntity.status(null).body("file upload failed");
		}
	}

	@PostMapping("/uploadData")
	public ResponseEntity<String> uploadData(@RequestBody List<CsvFile> csvFile) {
		try {
			csvFileService.uploadData(csvFile);
			return ResponseEntity.status(HttpStatus.OK).body("Data uploaded successfully");
		} catch (Exception e) {
			return ResponseEntity.status(null).body("data failed to upload");
		}
	}

	@GetMapping("/fetchAllData")
	public ResponseEntity<List<CsvFile>> fetchAllData() {
		try {
			List<CsvFile> list = csvFileService.fecthAllData();
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/fetchByCode/{code}")
	public ResponseEntity<CsvFile> fetchByCode(@PathVariable String code) {
		try {
			CsvFile csvFile = csvFileService.fecthByCode(code);
			return ResponseEntity.status(HttpStatus.OK).body(csvFile);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteAllData")
	public ResponseEntity<String> deleteAllData() {
		try {
			csvFileService.deleteAllData();
			return ResponseEntity.status(HttpStatus.OK).body("All data deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(null).body("failed to delete all data");
		}
	}

	@GetMapping("/file/downloadFile")
	public ResponseEntity<Resource> getFile() {
		InputStreamResource inputStream = new InputStreamResource(csvFileService.downloadFile());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "exercise.csv")
				.contentType(MediaType.parseMediaType("application/csv")).body(inputStream);
	}

}
