package br.com.paulomayumi.musictoalbum.controller;

import br.com.paulomayumi.musictoalbum.dto.MusicDto;
import br.com.paulomayumi.musictoalbum.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private MusicService service;

    @PostMapping
    public ResponseEntity<MusicDto> create(@RequestBody MusicDto musicDto){
        MusicDto city = service.create(musicDto);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicDto> findById(@PathVariable(name = "id") long id){
        MusicDto city = service.findById(id);
        this.buildSelfLink(city);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MusicDto> update(@RequestBody MusicDto musicDto){
        MusicDto city = service.update(musicDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") long id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<MusicDto>> findAll(){
        CollectionModel<MusicDto> cities  = CollectionModel.of(service.findAll());
        for(MusicDto city : cities){
            buildSelfLink(city);
        }
        this.buildCollectionLink(cities);
        return new ResponseEntity<CollectionModel<MusicDto>>(cities, HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<MusicDto>> findByName(@PathVariable(name = "name") String name){
        var cities = service.findByName(name);
        return new ResponseEntity<List<MusicDto>>(cities, HttpStatus.OK);
    }

    @GetMapping("/find/state/{state}")
    public ResponseEntity<List<MusicDto>> findByState(@PathVariable(name = "state") String state){
        var cities = service.findByState(state);
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
