package Ejercico_Skin.Skin_Ex.service;

import Ejercico_Skin.Skin_Ex.Excepciones.*;
import Ejercico_Skin.Skin_Ex.entity.Skin;
import Ejercico_Skin.Skin_Ex.entity.Usuario;
import Ejercico_Skin.Skin_Ex.repository.SkinRepositori;
import Ejercico_Skin.Skin_Ex.repository.UsuarioRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepositori usuarioRepositori;

    @Autowired
    private SkinRepositori skinRepositori;
    private List<Usuario> listaUsuarios;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        if (usuario.getNombre().equals("") || usuario.getNombre() == null) {
            throw new NotFoundException("p-401", HttpStatus.BAD_REQUEST, "Nombre de usuario requerido");

        }
        if (usuario.getApellido().equals("") || usuario.getApellido() == null) {
            throw new NotFoundException("p-402", HttpStatus.BAD_REQUEST, "Apellido de usuario requerido");

        }
        Usuario miUsuario = usuarioRepositori.save(usuario);
        return miUsuario;
    }

    @Override
    public Optional<Usuario> buscarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepositori.findById(id);
        System.out.println("impresion del valor del ID1: " + id);
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se ha encontrado el usuario");
        } else if (id == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "No se ha introducido el campo ID");
            //throw new BadRequestException("Se necesita introducir un Id",new ExceptionDetails("Se necesita introducir un Id22","p-501"));
        } else if (!(id instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "El formato del campo ID no es correcto");
            // throw new BadRequestException("Se necesita introducir un Id",new ExceptionDetails("Se necesita introducir un Id22","p-501"));
        } else {
            return usuarioOptional;
        }
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepositori.findById(usuario.getId());
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se ha encontrado el Usuario a actualizar");
        }
        if (usuario.getNombre().equals("") || usuario.getNombre() == null) {
            throw new NotFoundException("p-401", HttpStatus.BAD_REQUEST, "Nombre de usuario requerido");

        }
        if (usuario.getApellido().equals("") || usuario.getApellido() == null) {
            throw new NotFoundException("p-402", HttpStatus.BAD_REQUEST, "Apellido de usuario requerido");

        }
        if (usuario.getId() == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
        if (!(usuario.getId() instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "Formato campo ID incorrecto");
        } else {
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
        List<Usuario> listaUsuarios = usuarioRepositori.findAll();
        if (listaUsuarios.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se han encontrado elementos a mostrar");
        }
        return listaUsuarios;
    }

    @Override
    public List<Skin> listadoSkinsUsuarioId(Long usuario_id) {
        Optional<Usuario> usuarioOptional = usuarioRepositori.findById(usuario_id);
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("p-401", HttpStatus.NOT_FOUND, "No se ha encontrado el usuario");
        }
        if (usuario_id == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
        if (!(usuario_id instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "Formato campo ID incorrecto");
        }
        if (usuarioOptional.get().getSkins().isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "Este usuario no ha comprado ningun skin");
        } else {
            List<Skin> listadoSkins = usuarioOptional.get().getSkins();
            return listadoSkins;
        }
    }

    @Override
    public Skin deleteSkinUsuario(Usuario usuario, Long skin_id) {
        if (usuario.getId() == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
        if (!(usuario.getId() instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "Formato campo ID incorrecto");
        }
        Optional<Usuario> usuarioOptional = usuarioRepositori.findById(usuario.getId());
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se ha encontrado el usuario");
        }
        if (skin_id == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
        if (!(skin_id instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "Formato campo ID incorrecto");
        }
        Optional<Skin> skinOptional = skinRepositori.findById(skin_id);
        if (skinOptional.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se ha encontrado el skin a borrar");
        }
        List<Skin> listadoSkins = usuarioOptional.get().getSkins();
        ListIterator<Skin> it = listadoSkins.listIterator();
        Skin miSkin = new Skin();
        int i = 0;
        while (it.hasNext()) {
            miSkin = it.next();
            if (listadoSkins.get(i).getId() == skin_id) {
                it.remove();
                skinRepositori.deleteById(skin_id);
                return miSkin;
            }
            i++;
        }
        return miSkin;
    }

    @Override
    public void deleteUsuario(Long id) {
        if (id == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
        if (!(id instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "Formato campo ID incorrecto");
        }
        Optional<Usuario> usuarioOptional = usuarioRepositori.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se ha encontrado al usuario");
        }
        usuarioRepositori.deleteById(id);
        //return usuarioOptional.get();
    }

    @Override
    public void deleteAllUsuarios() {
        usuarioRepositori.deleteAll();
    }


}
