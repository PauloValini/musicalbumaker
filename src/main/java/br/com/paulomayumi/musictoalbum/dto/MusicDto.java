package br.com.paulomayumi.musictoalbum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MusicDto extends RepresentationModel<MusicDto> {

    private long id;
    private String title;
    private String musicwriter;

    public String getTitleAndWriter(){
        return title + "/" + musicwriter;
    }

}
