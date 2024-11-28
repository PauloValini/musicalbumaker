package br.com.paulomayumi.musictoalbum.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "album")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlbumModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(length = 50, nullable = false)
    private String artist;

    @Column(name = "releaseDate", nullable = false)
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private MusicModel music;


}
