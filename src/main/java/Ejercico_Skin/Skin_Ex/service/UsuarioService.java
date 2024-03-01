package Ejercico_Skin.Skin_Ex.service;


import Ejercico_Skin.Skin_Ex.Excepciones.BadRequestException;
import Ejercico_Skin.Skin_Ex.entity.Skin;
import Ejercico_Skin.Skin_Ex.entity.Usuario;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario);
    Optional<Usuario> buscarUsuario(Long id) throws BadRequestException;
    Usuario actualizarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    List<Skin> listadoSkinsUsuarioId(Long usuario_id) ;
    Skin deleteSkinUsuario(Usuario usuario,Long skin_id);
    void deleteUsuario(Long id);

    void deleteAllUsuarios();

}
