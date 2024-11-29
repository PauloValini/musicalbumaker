package br.com.paulomayumi.musictoalbum.controller;

import br.com.paulomayumi.musictoalbum.dto.AlbumDto;
import br.com.paulomayumi.musictoalbum.exception.CustomExceptionResponse;
import br.com.paulomayumi.musictoalbum.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Album", description = "Endpoint usado para operações que envolvem Albums")
@RestController
@RequestMapping("/api/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cria ou cadastra um novo Album", tags = {"Album"},responses = {
            @ApiResponse(description = "CREATED", responseCode = "201", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AlbumDto.class)
            )}),
    })
    public ResponseEntity<AlbumDto> create(@RequestBody AlbumDto albumDto){
        AlbumDto album = albumService.create(albumDto);
        return new ResponseEntity<>(album, HttpStatus.CREATED);
    }


    @Operation(summary = "Recuperar um Album mediante um ID informado", tags = {"Album"},
        responses = {
                @ApiResponse(description = "Album recuperado com sucesso!", responseCode = "200", content = {@Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = AlbumDto.class)
                )}),
                @ApiResponse(description = "Resource not found", responseCode = "404", content = {@Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = CustomExceptionResponse.class)
                )})
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> findById(@PathVariable(name = "id") long id){
        AlbumDto album = albumService.findById(id);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AlbumDto> update(@RequestBody AlbumDto albumDto){
        AlbumDto albumUpdated = albumService.update(albumDto);
        return new ResponseEntity<>(albumUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") long id){
        albumService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<AlbumDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<AlbumDto> assembler
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
        Page<AlbumDto> album = albumService.findAll(pageable);
        return new ResponseEntity(assembler.toModel(album), HttpStatus.OK);

    }

}
