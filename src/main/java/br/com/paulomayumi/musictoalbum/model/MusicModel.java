package br.com.paulomayumi.musictoalbum.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "music")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumModel album;
}
