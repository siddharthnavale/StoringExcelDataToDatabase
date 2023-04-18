package com.example.demo.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Mobile;
import com.example.demo.Helper.Helper;
import com.example.demo.Service.ExcelService;

@RestController
public class ExcelDownloadContoller {

	@Autowired
	private ExcelService excelService;

	@Autowired
	private Helper helper;

	@PostMapping("/convertExcelToDatabase")
	public ResponseEntity<String> convertExcelToDatabase(@RequestParam("file") MultipartFile file) {
		if(helper.checkContentType(file)) {
			excelService.save(file);
			return ResponseEntity.ok("File Uploaded and Data saved in Database");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please "
					+ "Upload Excel File");
		}}
	
    @GetMapping("/showContentOfDatabase")
    public List<Mobile> getAllMobiles(){
    	
		return excelService.getAllProducts();

	}

}
