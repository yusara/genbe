package com.afdhal.genbe.repository;

import com.afdhal.genbe.model.entity.Pendidikan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PendidikanRepository extends JpaRepository<Pendidikan, Integer> {
    @Query(value = "SELECT jenjang FROM t_pendidikan WHERE id_person = ?1 ORDER BY tahunlulus DESC LIMIT 1", nativeQuery = true)
    String cariPendidikanTerakhir(Integer id);
}
