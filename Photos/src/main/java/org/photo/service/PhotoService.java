package org.photo.service;

import org.photo.model.Photo;
import org.photo.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Iterable<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo get(Integer id) {
        return photoRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photoRepository.deleteById(id);
    }

    public Photo save(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setId((int)(Math.random() * 100));
        photo.setContentType(file.getContentType());
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());
        photoRepository.save(photo);
        return photo;
    }
}