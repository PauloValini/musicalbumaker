package br.com.paulomayumi.musictoalbum.repository;

import br.com.paulomayumi.musictoalbum.model.AlbumModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumModel, Long> {

    public Page<AlbumModel> findAll(Pageable pageable);

}
