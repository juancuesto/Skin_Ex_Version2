package Ejercico_Skin.Skin_Ex.service;

import Ejercico_Skin.Skin_Ex.Excepciones.EcceptionSkinNotFound;
import Ejercico_Skin.Skin_Ex.Excepciones.ExcepcionSkinNoDisponible;
import Ejercico_Skin.Skin_Ex.Excepciones.ExceptionCrearUsuario;
import Ejercico_Skin.Skin_Ex.Excepciones.ExceptionUsuarioNotFound;
import Ejercico_Skin.Skin_Ex.entity.Skin;
import Ejercico_Skin.Skin_Ex.entity.Usuario;
import Ejercico_Skin.Skin_Ex.repository.SkinRepositori;
import Ejercico_Skin.Skin_Ex.repository.UsuarioRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepositori usuarioRepositori;

    @Autowired
    private SkinRepositori skinRepositori;

    @Override
    public Usuario crearUsuario(Usuario usuario) throws ExceptionCrearUsuario {
        Usuario miUsuario=usuarioRepositori.save(usuario);
        if(miUsuario!=null){
            return miUsuario;
        }else {
            throw new ExceptionCrearUsuario("Error al crear el usuario");
        }
    }

    @Override
    public Optional<Usuario> buscarUsuario(Long id) throws ExceptionUsuarioNotFound {
        Optional<Usuario> usuarioOptional=usuarioRepositori.findById(id);
        if(usuarioOptional.isEmpty()){
            throw new ExceptionUsuarioNotFound("El usuario no se ha encontrado");
        }else {
            return usuarioOptional;
        }
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario, Long usuario_id) throws ExceptionUsuarioNotFound {
        Optional<Usuario> usuarioOptional=usuarioRepositori.findById(usuario_id);
            if(usuarioOptional.isEmpty()){
                throw new ExceptionUsuarioNotFound("No se ha encontrado el Usuario a actualizar");
            }else {
                usuarioOptional.get().setId(usuario.getId());
                usuarioOptional.get().setNombre(usuario.getNombre());
                usuarioOptional.get().setApellido(usuario.getApellido());
                usuarioOptional.get().setEdad(usuario.getEdad());
                usuarioOptional.get().setSaldo(usuario.getSaldo());
                usuarioOptional.get().setSkins(usuario.getSkins());

                return usuarioRepositori.save(usuarioOptional.get());
            }

    }
    @Override
    public List<Usuario> listarUsuarios() {
        return null;
    }

    @Override
    public List<Skin> listadoSkinsUsuarioId(Long usuario_id) throws ExceptionUsuarioNotFound {
        Optional<Usuario> usuarioOptional=usuarioRepositori.findById(usuario_id);
        if(usuarioOptional.isEmpty()){
            throw new ExceptionUsuarioNotFound("No se ha encntrado el usuario");
        }else {
            List<Skin> listadoSkins=usuarioOptional.get().getSkins();
            return listadoSkins;
        }
    }

    @Override
    public Skin deleteSkinUsuario(Usuario usuario, Long skin_id) throws ExceptionUsuarioNotFound, EcceptionSkinNotFound,ExcepcionSkinNoDisponible {
        Optional<Usuario> usuarioOptional=usuarioRepositori.findById(usuario.getId());
        if(usuarioOptional.isEmpty()){
            throw new ExceptionUsuarioNotFound("No se ha encontrado el usuario");
        }
        Optional<Skin> skinOptional=skinRepositori.findById(skin_id);
        if(skinOptional.isEmpty()){
            throw new EcceptionSkinNotFound("No se ha encontrado el skin");
        }
        List<Skin> listadoSkins=usuarioOptional.get().getSkins();
        ListIterator<Skin> it=listadoSkins.listIterator();
        Skin miSkin=new Skin();
        int i=0;
       while(it.hasNext()){
           miSkin=it.next();
           if(listadoSkins.get(i).getId()==skin_id){
               it.remove();
               skinRepositori.deleteById(skin_id);
               return miSkin;
           }
           i++;
       }
       throw new ExcepcionSkinNoDisponible("Este usuario no ha comprado este skin");
    }

    @Override
    public void deleteAllUsuarios() {
        usuarioRepositori.deleteAll();
    }


}
