package br.com.paulomayumi.musictoalbum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AlbumDto {

    private long id;
    private String title;
    private String artist;
    private Date releaseDate;
}
