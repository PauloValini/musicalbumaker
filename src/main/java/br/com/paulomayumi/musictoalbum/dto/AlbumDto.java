package br.com.paulomayumi.musictoalbum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AlbumDto {

    private long id;
    private String fullName;
    private String gender;
    private Date birthDay;
    private MusicDto city;
}
