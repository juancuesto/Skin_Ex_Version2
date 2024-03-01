package Ejercico_Skin.Skin_Ex.service;



import Ejercico_Skin.Skin_Ex.Excepciones.*;

import Ejercico_Skin.Skin_Ex.entity.Skin;
import Ejercico_Skin.Skin_Ex.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface SkinService {

    List<Skin> listadoSkinsDispponibles();

    Optional<Skin> buscarSkinByName(Skin skin) ;
    List<Skin> listarSkins();
    Skin obtenerSkinById(Long skin_id);

    Skin actualizarSkin(Skin skin,Long usuario_id) ;
    Skin comprarSkin(Skin skin,Long usuario_id);
    Optional<Skin> buscarSkin(Long skin_id);
    void deleteAllSkins();
}
