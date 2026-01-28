package com.example.springBoot2.controllers;

import com.example.springBoot2.models.Album;
import com.example.springBoot2.repositories.AlbumRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {


    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping
    public List<Album> getAlbums(){
        return  albumRepository.findAll();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable int id){
        return albumRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Album addAlbum(@RequestBody Album album){
        return albumRepository.save(album);
    }

    @PostMapping("/addBulk")
    public List<Album> addBulkAlbum(@RequestBody List<Album> albums) {
        return albumRepository.saveAll(albums);
    }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable int id , @RequestBody Album album){
        Album album1 = albumRepository.findById(id).orElse(null);
        album1.setName(album.getName());
        album1.setArtist(album.getArtist());
        album1.setTracks(album.getTracks());
        album1.setYear(album.getYear());
        return albumRepository.save(album1);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable int id){
        albumRepository.deleteById(id);
    }

}