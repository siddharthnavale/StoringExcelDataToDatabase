package com.example.demo.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Mobile;
import com.example.demo.Helper.Helper;
import com.example.demo.Repositoty.MobileRepository;

@Service
public class ExcelService {
	
	@Autowired
	private MobileRepository mobileRepository;
	
	@Autowired
	private Helper helper;
	
	public void save (MultipartFile file){
		
			try {
				List<Mobile> mobiles = helper.convertFileToDatabase(file.getInputStream());
				mobileRepository.saveAll(mobiles);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	
	}
	
	public List<Mobile> getAllProducts(){
		return mobileRepository.findAll();
		}
	}

