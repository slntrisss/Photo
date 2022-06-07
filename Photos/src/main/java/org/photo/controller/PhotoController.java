package org.photo.controller;

import org.photo.service.PhotoService;
import org.photo.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class PhotoController {
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photos")
    public Iterable<Photo> getPhotos(){
        return photoService.getAllPhotos();
    }

    @GetMapping("/photo/{id}")
    public Photo getPhoto(@PathVariable("id") Integer id){
        Photo photo = photoService.get(id);
        if(photo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photo/{id}")
    public void deletePhoto(@PathVariable("id") Integer id){
        photoService.remove(id);
    }

    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photoService.save(file);
    }

}
