package Ejercico_Skin.Skin_Ex.service;



import Ejercico_Skin.Skin_Ex.Excepciones.EcceptionSkinNotFound;
import Ejercico_Skin.Skin_Ex.Excepciones.ExcepcionSkinNoDisponible;
import Ejercico_Skin.Skin_Ex.Excepciones.ExceptionCrearUsuario;
import Ejercico_Skin.Skin_Ex.Excepciones.ExceptionUsuarioNotFound;
import Ejercico_Skin.Skin_Ex.entity.Skin;
import Ejercico_Skin.Skin_Ex.entity.Usuario;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario)throws ExceptionCrearUsuario;
    Optional<Usuario> buscarUsuario(Long id) throws ExceptionUsuarioNotFound;
    Usuario actualizarUsuario(Usuario usuario,Long usuario_id)throws ExceptionUsuarioNotFound;
    List<Usuario> listarUsuarios();
    List<Skin> listadoSkinsUsuarioId(Long usuario_id) throws ExceptionUsuarioNotFound;
    Skin deleteSkinUsuario(Usuario usuario,Long skin_id) throws ExceptionUsuarioNotFound, EcceptionSkinNotFound, ExcepcionSkinNoDisponible;

    void deleteAllUsuarios();

}
