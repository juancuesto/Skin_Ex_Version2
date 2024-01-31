package Ejercico_Skin.Skin_Ex.repository;

import Ejercico_Skin.Skin_Ex.entity.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkinRepositori extends JpaRepository<Skin,Long> {

    @Query(value = "select s from Skin s where s.nombre = :nombre")
    public Optional<Skin> buscarSkinByName(@Param("nombre") String nombre);
}
