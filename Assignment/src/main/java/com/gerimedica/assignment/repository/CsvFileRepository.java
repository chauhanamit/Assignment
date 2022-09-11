package com.gerimedica.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gerimedica.assignment.model.CsvFile;

public interface CsvFileRepository extends JpaRepository<CsvFile, String> {

	CsvFile findByCode(String code);
}
