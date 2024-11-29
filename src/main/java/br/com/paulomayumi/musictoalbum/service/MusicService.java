package br.com.paulomayumi.musictoalbum.service;

import br.com.paulomayumi.musictoalbum.dto.MusicDto;
import br.com.paulomayumi.musictoalbum.exception.ResourceNotFoundException;
import br.com.paulomayumi.musictoalbum.mapper.CustomModelMapper;
import br.com.paulomayumi.musictoalbum.model.AlbumModel;
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
        MusicModel music = CustomModelMapper.parseObject(musicDto, MusicModel.class);
        return CustomModelMapper.parseObject(repository.save(music), MusicDto.class);
    }

    public MusicDto findById(long id){
        MusicModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Musica não encontrada")
        );
        return CustomModelMapper.parseObject(found, MusicDto.class);
    }

    public MusicDto update(MusicDto musicDto){
        MusicModel musicModel = repository.findById(musicDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Musica não encontrada"));
        musicModel.setTitle(musicDto.getTitle());
        musicModel.setAlbum(CustomModelMapper.parseObject(musicDto.getAlbum(), AlbumModel.class));
        return CustomModelMapper.parseObject(repository.save(musicModel), MusicDto.class);
    }

    public void delete(long id){
        MusicModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Musica não encontrada"));
        repository.delete(found);
    }

    public List<MusicDto> findAll(){
        var list = repository.findAll();
        return CustomModelMapper.parseObjectList(list, MusicDto.class);
    }

    public List<MusicDto> findByTitle(String name){
        var musica = repository.findByTitleContainsIgnoreCaseOrderByTitle(name);
        return CustomModelMapper.parseObjectList(musica, MusicDto.class);
    }
}
