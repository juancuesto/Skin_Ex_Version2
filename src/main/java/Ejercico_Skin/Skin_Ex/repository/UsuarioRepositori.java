package Ejercico_Skin.Skin_Ex.repository;

import Ejercico_Skin.Skin_Ex.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositori extends JpaRepository<Usuario,Long> {
}
