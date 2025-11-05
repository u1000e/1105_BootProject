package com.kh.start.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {
	
	private final Path fileLocation;
	
	public FileService() {
		this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
	}
	
	public String store(MultipartFile file) {
		// 이름바꾸기 메소드 호출~~ (패스~~)
		String originalFilename = file.getOriginalFilename();
		Path targetLocation = this.fileLocation.resolve(originalFilename);
		
		try {
			
			Files.copy(file.getInputStream(), 
					targetLocation,
					StandardCopyOption.REPLACE_EXISTING);
			
			return "http://localhost:8081/uploads/" + originalFilename;
		} catch (IOException e) {
			throw new RuntimeException("이상요상 파일임 ㅎ");
		}
	}
	
	
	
	

}
