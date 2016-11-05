package com.example.Layercontroller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Layerservice.CustomerService;
import com.example.pojo.FileUpload;

@RestController
@MultipartConfig(fileSizeThreshold = 20971520)
public class fileController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/uploadFile",method = RequestMethod.POST, consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void uploadFile(@RequestParam("uploadedFile") MultipartFile uploadFile) throws IOException {
		System.out.println("Started");
		FileUpload fileUpload = new FileUpload();
		fileUpload.setFileName(uploadFile.getOriginalFilename());
		fileUpload.setData(uploadFile.getBytes());
		customerService.saveFile(fileUpload);
	}
	
	@RequestMapping(value="/downloadFile/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") int id) throws IOException {
		FileUpload fileUpload = customerService.getFile(id);
		
		if( fileUpload == null ) {
			return null;
		}
		ByteArrayInputStream o = new ByteArrayInputStream(fileUpload.getData());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("Content-Disposition", "attachment; filename="+fileUpload.getFileName());
	    
		return ResponseEntity.ok().contentLength(fileUpload.getData().length)
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.headers(headers)
				.body(new InputStreamResource(o));
	}
}

