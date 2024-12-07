package br.com.paulomayumi.musictoalbum.service;

import br.com.paulomayumi.musictoalbum.dto.AlbumDto;
import br.com.paulomayumi.musictoalbum.exception.ResourceNotFoundException;
import br.com.paulomayumi.musictoalbum.mapper.CustomModelMapper;
import br.com.paulomayumi.musictoalbum.model.MusicModel;
import br.com.paulomayumi.musictoalbum.model.AlbumModel;
import br.com.paulomayumi.musictoalbum.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository repository;

    public AlbumDto create(AlbumDto albumDto){
        AlbumModel albumModel = CustomModelMapper.parseObject(albumDto, AlbumModel.class);
        return CustomModelMapper.parseObject(repository.save(albumModel), AlbumDto.class);
    }

    public AlbumDto findById(long id){
        AlbumModel albumModel = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Album não encontrado!"));
        return CustomModelMapper.parseObject(albumModel, AlbumDto.class);
    }

    public AlbumDto update(AlbumDto albumDto){
        AlbumModel albumModel = repository.findById(albumDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Album não encontrado!")
        );
        albumModel.setReleaseDate(albumDto.getReleaseDate());
        albumModel.setArtist(albumDto.getArtist());
        albumModel.setTitle(albumDto.getTitle());
        albumModel.setReleaseDate(albumDto.getReleaseDate());
        return CustomModelMapper.parseObject(repository.save(albumModel), AlbumDto.class);
    }

    public void delete(long id){
        AlbumModel albumModel = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Album não encontrado!")
        );
        repository.delete(albumModel);
    }

    public Page<AlbumDto> findAll(Pageable pageable){
        var album = repository.findAll(pageable);
        return album.map( c -> CustomModelMapper.parseObject(c, AlbumDto.class));
    }

}
