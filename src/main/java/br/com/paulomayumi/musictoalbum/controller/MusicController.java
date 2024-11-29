package br.com.paulomayumi.musictoalbum.controller;

import br.com.paulomayumi.musictoalbum.dto.MusicDto;
import br.com.paulomayumi.musictoalbum.service.MusicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Music", description = "Endpoint usado para operações que envolvem Musicas")
@RestController
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private MusicService service;

    @PostMapping
    public ResponseEntity<MusicDto> create(@RequestBody MusicDto musicDto){
        MusicDto music = service.create(musicDto);
        return new ResponseEntity<>(music, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicDto> findById(@PathVariable(name = "id") long id){
        MusicDto music = service.findById(id);
        this.buildSelfLink(music);
        return new ResponseEntity<>(music, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MusicDto> update(@RequestBody MusicDto musicDto){
        MusicDto music = service.update(musicDto);
        return new ResponseEntity<>(music, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") long id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<MusicDto>> findAll(){
        CollectionModel<MusicDto> musics  = CollectionModel.of(service.findAll());
        for(MusicDto music : musics){
            buildSelfLink(music);
        }
        this.buildCollectionLink(musics);
        return new ResponseEntity<CollectionModel<MusicDto>>(musics, HttpStatus.OK);
    }

    @GetMapping("/find/title/{title}")
    public ResponseEntity<List<MusicDto>> findByTitle(@PathVariable(name = "title") String name){
        var musics = service.findByTitle(name);
        return new ResponseEntity<List<MusicDto>>(musics, HttpStatus.OK);
    }

    @GetMapping("/find/musicwriter/{musicwriter}")
    public ResponseEntity<List<MusicDto>> findByMusicwriter(@PathVariable(name = "musicwriter") String state){
        var cities = service.findByMusicwriter(state);
        return new ResponseEntity<List<MusicDto>>(cities, HttpStatus.OK);
    }

    private void buildSelfLink(MusicDto musicDto){
        //self link
        musicDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findById(musicDto.getId())
                        ).withSelfRel()
                );
    }

    public void buildCollectionLink(CollectionModel<MusicDto> cities){
        cities.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findAll()
                ).withRel(LinkRelation.of("COLLECTION"))
        );
    }
}
