package br.com.paulomayumi.musictoalbum.service;

import br.com.paulomayumi.musictoalbum.dto.MusicDto;
import br.com.paulomayumi.musictoalbum.exception.ResourceNotFoundException;
import br.com.paulomayumi.musictoalbum.mapper.CustomModelMapper;
import br.com.paulomayumi.musictoalbum.model.MusicModel;
import br.com.paulomayumi.musictoalbum.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository repository;

    public MusicDto create(MusicDto musicDto){
        MusicModel city = CustomModelMapper.parseObject(musicDto, MusicModel.class);
        return CustomModelMapper.parseObject(repository.save(city), MusicDto.class);
    }

    public MusicDto findById(long id){
        MusicModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cidade não encontrada")
        );
        return CustomModelMapper.parseObject(found, MusicDto.class);
    }

    public MusicDto update(MusicDto musicDto){
        MusicModel found = repository.findById(musicDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Cidade não encontrada"));
        found.setTitle(musicDto.getTitle());
        found.setMusicwriter(musicDto.getMusicwriter());
        return CustomModelMapper.parseObject(repository.save(found), MusicDto.class);
    }

    public void delete(long id){
        MusicModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cidade não encontrada"));
        repository.delete(found);
    }

    public List<MusicDto> findAll(){
        var list = repository.findAll();
        return CustomModelMapper.parseObjectList(list, MusicDto.class);
    }

    public List<MusicDto> findByName(String name){
        var cities = repository.findByNameContainsIgnoreCaseOrderByName(name);
        return CustomModelMapper.parseObjectList(cities, MusicDto.class);
    }

    public List<MusicDto> findByState(String state){
        var cities = repository.findByStateEqualsIgnoreCaseOrderByStateAscNameAsc(state);
        return CustomModelMapper.parseObjectList(cities, MusicDto.class);
    }



}