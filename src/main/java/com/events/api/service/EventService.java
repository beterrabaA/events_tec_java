package com.events.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.events.api.domain.event.Event;
import com.events.api.domain.event.EventRequestDTO;
import com.events.api.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {
    @Value("${aws.bucket.name}")
    String bucketName;

    @Autowired
    private AmazonS3 s3;

    @Autowired
    private EventRepository repository;

    public Event createEvent(EventRequestDTO data) {
        String imageUrl = null;

        if (data.image() != null) {
            imageUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImageUrl(imageUrl);
        newEvent.setRemote(data.remote());

        repository.save(newEvent);

        return newEvent;
    };

    private String uploadImg(MultipartFile file) {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        try {
            File convertedFile = this.convertMultipartToFile(file);
            s3.putObject(bucketName,fileName, convertedFile);
            convertedFile.delete();
            return s3.getUrl(bucketName,fileName).toString();
        } catch (Exception e) {
            System.out.println("error: upload file went wrong.");
            return "null";
        }
    }

    private File convertMultipartToFile(MultipartFile file) throws IOException {
        File newFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(file.getBytes());
        fos.close();
        return  newFile;
    }
}
