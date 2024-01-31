package Ejercico_Skin.Skin_Ex.service;

import Ejercico_Skin.Skin_Ex.Excepciones.*;
import Ejercico_Skin.Skin_Ex.Excepciones.ExcepcionSkinNoDisponible;
import Ejercico_Skin.Skin_Ex.Excepciones.ExceptionUsuarioNotFound;
import Ejercico_Skin.Skin_Ex.entity.Skin;
import Ejercico_Skin.Skin_Ex.entity.Usuario;
import Ejercico_Skin.Skin_Ex.repository.SkinRepositori;
import Ejercico_Skin.Skin_Ex.repository.UsuarioRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SkinServiceImpl implements SkinService {

    @Autowired
    private SkinRepositori skinRepositori;

    @Autowired
    private UsuarioRepositori usuarioRepositori;

    @Override
    public List<Skin> listadoSkinsDispponibles() throws ExcepcionSkinNoDisponible {
        try {
            FileReader entrada = new FileReader(".\\src\\main\\resources\\data\\skins.json");//Ruta relativa

            BufferedReader miBuffer = new BufferedReader(entrada);

            String linea = "";
            while (linea != null) {
                linea = miBuffer.readLine();
                Skin miSkin = new Skin();
                if (linea != null) {
                    System.out.println(linea);

                    String p1;
                    String p2;
                    //Intento crear los skins a partir del fichero para crear un ArrayList de skins que pueda mostrar con Postman
                    if (linea.contains(":")) {
                        String[] parts = linea.split(":");
                        p1 = parts[0];
                        p2 = parts[1];

                        if (p1.equalsIgnoreCase("nombre")) {
                            miSkin.setNombre(p2);
                        } else if (p1.equalsIgnoreCase("configuracion")) {
                            boolean a = Boolean.parseBoolean(p2);
                            miSkin.setConfiguracion(a);
                        } else if (p1.equalsIgnoreCase("color")) {
                            miSkin.setColor(p2);
                        } else if (p1.equalsIgnoreCase("precio")) {
                            double precio = Double.parseDouble(p2);
                            miSkin.setPrecio(precio);
                        } else if (p1.equalsIgnoreCase("Id")) {
                            Long id = Long.parseLong(p2);
                            miSkin.setId(id);
                        }
                    }
                }
                // System.out.println("El skin obtenido es------------------" + miSkin);
            }

            entrada.close();
        } catch (IOException e) {
            throw new ExcepcionSkinNoDisponible("No se ha podido cargar los Skins disponibles");
        }

        return null;
    }

    @Override
    public Optional<Skin> buscarSkinByName(Skin skin) throws ExcepcionSkinNoDisponible {
        Optional<Skin> skinOptional = skinRepositori.buscarSkinByName(skin.getNombre());
        if (skinOptional.isEmpty()) {
            throw new ExcepcionSkinNoDisponible("El Skin no esta dispoonible");
        } else {
            return skinOptional;
        }
    }

    @Override
    public List<Skin> listarSkins() {
        return skinRepositori.findAll();
    }

    @Override
    public Skin obtenerSkinById(Long skin_id) throws EcceptionSkinNotFound {
        Optional<Skin> skinOptional = skinRepositori.findById(skin_id);
        if (skinOptional.isEmpty()) {
            throw new EcceptionSkinNotFound("No se ha encontrado el skin");
        } else {
            return skinOptional.get();
        }
    }

    @Override
    public Skin actualizarSkin(Skin skin, Long usurious_id) throws ExceptionUsuarioNotFound, ExcepcionSkinNoDisponible, EcceptionSkinNotFound {
        Optional<Usuario> usuarioOptional = usuarioRepositori.findById(usurious_id);
        if (usuarioOptional.isEmpty()) {
            throw new ExceptionUsuarioNotFound("No se ha encontrado al usuario");
        }
        Optional<Skin> skinOptional = skinRepositori.buscarSkinByName(skin.getNombre());
        if (skinOptional.isEmpty()) {
            throw new EcceptionSkinNotFound("No se encuentra la skin");
        }
        if (usuarioOptional.isPresent()) {
            List<Skin> listaSkins = usuarioOptional.get().getSkins();
            for (Skin ele:listaSkins) {
                if(ele.getId()== skin.getId()){
                    listaSkins.remove(ele);
                    listaSkins.add(skin);
                    usuarioOptional.get().setSkins(listaSkins);
                    usuarioRepositori.save(usuarioOptional.get());
                }else {
                    throw new ExcepcionSkinNoDisponible("Este usuario no ha comprado este skin");
                }
            }
        }
        return skinRepositori.save(skin);
    }

    @Override
    public void borrarSkin(Usuario usuario, Long id) throws ExceptionUsuarioNotFound, ExcepcionSkinNoDisponible {

    }


    @Override
    public Skin comprarSkin(Skin skin, Long usuario_id) throws ExceptionComprarSkin, ExcepcionSkinNoDisponible, ExceptionUsuarioNotFound {
        Optional<Skin> skinOptional = skinRepositori.buscarSkinByName(skin.getNombre());
        Optional<Usuario> usuarioOptional = usuarioRepositori.findById(usuario_id);
        if (skinOptional.isEmpty()) {
            if (usuarioOptional.isPresent()) {
                if (usuarioOptional.get().getSaldo() >= skin.getPrecio()) {
                    usuarioOptional.get().afegirSkin(skin);
                    double saldoInicial = usuarioOptional.get().getSaldo();
                    double saldoFinal = saldoInicial - skin.getPrecio();
                    usuarioOptional.get().setSaldo(saldoFinal);
                    usuarioRepositori.save(usuarioOptional.get());
                    return skinRepositori.save(skin);
                } else {
                    throw new ExceptionComprarSkin("El usuario no tiene saldo suficiente para realizar la compra");
                }

            } else {
                throw new ExceptionUsuarioNotFound("No se ha encontrado al usuario");
            }
        } else {
            throw new ExcepcionSkinNoDisponible("el skin no esta disponible");
        }
    }

    @Override
    public Optional<Skin> buscarSkin(Long skin_id) {
        return Optional.empty();
    }

    @Override
    public void deleteAllSkins() {
        skinRepositori.deleteAll();
    }
}
