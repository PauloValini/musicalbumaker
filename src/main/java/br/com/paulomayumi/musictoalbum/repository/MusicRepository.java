package br.com.paulomayumi.musictoalbum.repository;

import br.com.paulomayumi.musictoalbum.model.MusicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<MusicModel, Long> {

    public List<MusicModel> findByNameContainsIgnoreCaseOrderByName(String name);

    public List<MusicModel> findByStateEqualsIgnoreCaseOrderByStateAscNameAsc(String state);

}
