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
public class
UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SkinService skinService;


    @PostMapping("/crear_usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.crearUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/buscar_usuarios/{id}")
    public ResponseEntity<?> buscarUsuarioById(@PathVariable Long id) throws BadRequestException{
        System.out.println("impresion del valor del ID2: " + id);
        if (id == null) {
            throw new NotFoundException("p-505", HttpStatus.BAD_REQUEST, "Necesitas ponr un Id");
        }
        return new ResponseEntity<>(usuarioService.buscarUsuario(id), HttpStatus.OK);

    }

    @GetMapping("/avalaible")
    public ResponseEntity<?> listarSkinsDisponibles() {
        List<Skin> listadoSkins = skinService.listadoSkinsDispponibles();
        if (listadoSkins.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listadoSkins);
    }

    @GetMapping("/findByName")
    public ResponseEntity<?> obeterSkinByName(@RequestBody Skin skin) {
            Optional<Skin> skinOptional = skinService.buscarSkinByName(skin);
            return new ResponseEntity<>(skinOptional.get(), HttpStatus.OK);
    }

    @PostMapping("buy/{usuario_id}")
    public ResponseEntity<?> comprarSkin(@RequestBody Skin skin, @PathVariable Long usuario_id) {
            return new ResponseEntity<>(skinService.comprarSkin(skin, usuario_id), HttpStatus.OK);
    }

    @GetMapping("myskins/{usuario_id}")
    public ResponseEntity<?> listadoSkinsUnUsuario(@PathVariable Long usuario_id) {
        List<Skin> listadoSkins = usuarioService.listadoSkinsUsuarioId(usuario_id);
        return new ResponseEntity<>(listadoSkins, HttpStatus.OK);
    }

    @DeleteMapping("delete/{skin_id}")
    public ResponseEntity<?> deleteSkinDeUnUsuario(@RequestBody Usuario usuario, @PathVariable Long skin_id) {
       return new ResponseEntity<>(usuarioService.deleteSkinUsuario(usuario,skin_id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/all/skins")
    public ResponseEntity<?> deleteAllSkins() {
        skinService.deleteAllSkins();
        return new ResponseEntity<>("Se han borrado todos los skins", HttpStatus.OK);
    }

    @DeleteMapping("/delete/all/usuarios")
    public ResponseEntity<?> deleteAllUsuarios() {
        usuarioService.deleteAllUsuarios();
        return new ResponseEntity<>("Se han borrado todos los usuarios", HttpStatus.OK);
    }

    @GetMapping(" ")
    public ResponseEntity<?> obtenerSkinById(@PathVariable("id") Long skin_id) {
            return new ResponseEntity<>(skinService.obtenerSkinById(skin_id), HttpStatus.OK);
    }

    @PutMapping("/color/{id}")
    public ResponseEntity<?> actualizarSkinCambioColor(@RequestBody Skin skin, @PathVariable Long id) {
            return new ResponseEntity<>(skinService.actualizarSkin(skin, id), HttpStatus.OK);
    }

    @GetMapping("/listar/usuarios")
    public ResponseEntity<?> listarUsuarios() {
        List<Usuario> listaUsuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
    }

    @PutMapping("/actaulizar/usuario")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.actualizarUsuario(usuario), HttpStatus.OK);
    }
    @DeleteMapping("/borrar_usuario/{id}")
    public ResponseEntity<?> borrarUsuarioById(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>("Elemento borrado correctamente",HttpStatus.OK);
    }
}

