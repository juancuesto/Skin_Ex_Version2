package Ejercico_Skin.Skin_Ex.controller;

import Ejercico_Skin.Skin_Ex.Excepciones.*;
import Ejercico_Skin.Skin_Ex.entity.Skin;
import Ejercico_Skin.Skin_Ex.entity.Usuario;
import Ejercico_Skin.Skin_Ex.service.SkinService;
import Ejercico_Skin.Skin_Ex.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skins")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SkinService skinService;


    @PostMapping("/crear_usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.crearUsuario(usuario), HttpStatus.CREATED);
        } catch (ExceptionCrearUsuario e) {
            return new ResponseEntity<>("Error al crear el usuario", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/buscar_usuarios")
    public ResponseEntity<?> buscarUsuarioById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(usuarioService.buscarUsuario(id), HttpStatus.OK);
        } catch (ExceptionUsuarioNotFound e) {
            return new ResponseEntity<>("No se ha encontrado el usuario", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/avalaible")
    public ResponseEntity<?> listarSkinsDisponibles() {
        try {
            return new ResponseEntity<>(skinService.listadoSkinsDispponibles(), HttpStatus.NOT_FOUND);
        } catch (ExcepcionSkinNoDisponible e) {
            return new ResponseEntity<>("Nos e ha podido encontrar los skins disponibles", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/findByName")
    public ResponseEntity<?> obeterSkinByName(@RequestBody Skin skin) {

        try {

            Optional<Skin> skinOptional = skinService.buscarSkinByName(skin);
            return new ResponseEntity<>("El skin no esta disponible", HttpStatus.NOT_FOUND);

        } catch (ExcepcionSkinNoDisponible e) {
            return new ResponseEntity<>(skin, HttpStatus.OK);
        }
    }

    @PostMapping("buy/{usuario_id}")
    public ResponseEntity<?> comprarSkin(@RequestBody Skin skin, @PathVariable Long usuario_id) {
        try {
            return new ResponseEntity<>(skinService.comprarSkin(skin, usuario_id), HttpStatus.OK);
        } catch (ExceptionUsuarioNotFound e) {
            return new ResponseEntity<>("No se ha encontrado el Usuario", HttpStatus.NOT_FOUND);
        } catch (ExcepcionSkinNoDisponible e) {
            return new ResponseEntity<>("El skin no estadisponible", HttpStatus.NOT_FOUND);
        } catch (ExceptionComprarSkin e) {
            return new ResponseEntity<>("El usuario no dispone de aldo suficiente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("myskins/{usuario_id}")
    public ResponseEntity<?> listadoSkinsUnUsuario(@PathVariable Long usuario_id){
        try {
            List<Skin> listadoSkins=usuarioService.listadoSkinsUsuarioId(usuario_id);
            if(listadoSkins.isEmpty()){
                return new ResponseEntity<>("Este usuario no ha comprado ningun skin",HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(listadoSkins,HttpStatus.OK);
            }
        }catch (ExceptionUsuarioNotFound e){
            return new ResponseEntity<>("No se ha encontrado al usuario",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("delete/{skin_id}")
    public ResponseEntity<?> deleteSkinDeUnUsuario(@RequestBody Usuario usuario,@PathVariable Long skin_id){
        try {
            return new ResponseEntity<>(usuarioService.deleteSkinUsuario(usuario,skin_id),HttpStatus.OK);
        }catch (ExceptionUsuarioNotFound e){
            return new ResponseEntity<>("No se ha encontrado al usuario",HttpStatus.NOT_FOUND);
        }catch (ExcepcionSkinNoDisponible e){
            return new ResponseEntity<>("Este usuario no ha comprado este skin",HttpStatus.NOT_FOUND);
        }catch (EcceptionSkinNotFound e){
            return new ResponseEntity<>("No se ha encontrado el skin",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/all/skins")
    public ResponseEntity<?> deleteAllSkins(){
        skinService.deleteAllSkins();
        return new ResponseEntity<>("Se han borrado todos los skins",HttpStatus.OK);
    }
    @DeleteMapping("/delete/all/usuarios")
    public ResponseEntity<?> deleteAllUsuarios(){
        usuarioService.deleteAllUsuarios();
        return new ResponseEntity<>("Se han borrado todos los usuarios",HttpStatus.OK);
    }
    @GetMapping("/getskin/{id}")
    public ResponseEntity<?> obtenerSkinById(@PathVariable("id") Long  skin_id){
        try {
            return new ResponseEntity<>(skinService.obtenerSkinById(skin_id),HttpStatus.OK);
        }catch (EcceptionSkinNotFound e){
            return new ResponseEntity<>("No se ha encontrado el skin",HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("/color/{id}")
    public ResponseEntity<?> actualizarSkinCambioColor(@RequestBody Skin skin,@PathVariable Long id){
        try {
            return new ResponseEntity<>(skinService.actualizarSkin(skin,id),HttpStatus.OK);
        }catch (EcceptionSkinNotFound e){
            return new ResponseEntity<>("no se ha encontrado el skin en BBDD",HttpStatus.NOT_FOUND);
        }catch (ExceptionUsuarioNotFound e){
            return new ResponseEntity<>("No se ha encontrado el usuario",HttpStatus.NOT_FOUND);
        }catch (ExcepcionSkinNoDisponible e){
            return new ResponseEntity<>("Este usuario no ha comprado esta skin",HttpStatus.NOT_FOUND);
        }
    }
}

