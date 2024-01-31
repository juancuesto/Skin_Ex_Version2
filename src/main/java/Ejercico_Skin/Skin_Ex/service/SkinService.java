package Ejercico_Skin.Skin_Ex.service;



import Ejercico_Skin.Skin_Ex.Excepciones.*;
import Ejercico_Skin.Skin_Ex.Excepciones.ExcepcionSkinNoDisponible;
import Ejercico_Skin.Skin_Ex.Excepciones.ExceptionUsuarioNotFound;
import Ejercico_Skin.Skin_Ex.entity.Skin;
import Ejercico_Skin.Skin_Ex.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface SkinService {

    List<Skin> listadoSkinsDispponibles()throws ExcepcionSkinNoDisponible;
    //Skin crearSkin(Skin skin,Long usuario_id) throws ExcepcionSkinNoDisponible;

    Optional<Skin> buscarSkinByName(Skin skin) throws ExcepcionSkinNoDisponible;
    List<Skin> listarSkins();
    Skin obtenerSkinById(Long skin_id) throws EcceptionSkinNotFound;

    Skin actualizarSkin(Skin skin,Long usurious_id) throws ExceptionUsuarioNotFound, EcceptionSkinNotFound,ExcepcionSkinNoDisponible;
    void borrarSkin(Usuario usuario,Long id) throws ExceptionUsuarioNotFound, ExcepcionSkinNoDisponible;
    Skin comprarSkin(Skin skin,Long usuario_id) throws ExceptionComprarSkin, ExcepcionSkinNoDisponible, ExceptionUsuarioNotFound;
    Optional<Skin> buscarSkin(Long skin_id);
    void deleteAllSkins();
}
