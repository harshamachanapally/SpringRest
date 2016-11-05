package com.example.Layerrepository;

import org.springframework.data.repository.CrudRepository;

import com.example.pojo.FileUpload;

public interface FileRepository extends CrudRepository<FileUpload, Integer> {
	
}
