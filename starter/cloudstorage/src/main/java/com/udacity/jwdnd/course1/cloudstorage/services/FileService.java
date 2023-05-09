package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
    public void storeFile(MultipartFile file, Integer userid) throws IOException {
        File newfile = new File();
        newfile.setUserid(userid);
        newfile.setFilename(file.getOriginalFilename());
        newfile.setFilesize(file.getSize());
        newfile.setContenttype(file.getContentType());
        newfile.setFiledata(file.getBytes());
        fileMapper.insert(newfile);
    }
    public File getFile(Integer fileId, Integer userid) {
        return fileMapper.getFile(fileId, userid);
    }
    public boolean isFile(MultipartFile file, Integer userid) {
        return fileMapper.isFile(file.getOriginalFilename(), userid);
    }
    public List<File> getFileNames(Integer userid) {
        return fileMapper.getFiles(userid);
    }
    public void deleteFile(Integer fileId, Integer userid) {
        fileMapper.deleteFile(fileId, userid);
    }

}

