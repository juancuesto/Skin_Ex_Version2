package Ejercico_Skin.Skin_Ex.service;

import Ejercico_Skin.Skin_Ex.Excepciones.*;
import Ejercico_Skin.Skin_Ex.entity.Skin;
import Ejercico_Skin.Skin_Ex.entity.Usuario;
import Ejercico_Skin.Skin_Ex.repository.SkinRepositori;
import Ejercico_Skin.Skin_Ex.repository.UsuarioRepositori;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SkinServiceImpl implements SkinService {

    private final Logger log = LoggerFactory.getLogger(SkinServiceImpl.class);

    @Autowired
    private SkinRepositori skinRepositori;

    @Autowired
    private UsuarioRepositori usuarioRepositori;

    @Override
    public List<Skin> listadoSkinsDispponibles() {
        List<String> lista = lecturaFichero();
        List<Skin> listadoSkins = new ArrayList<>();
        int i = 0;
        String linea = "";
        Skin miSkin = new Skin();
        while (linea != null) {
            boolean b = true;
            linea = lista.get(i);
            if (linea != null) {
                String p1;
                String p2;
                if (linea.contains(":")) {
                    String[] parts = linea.split(":");
                    p1 = parts[0];
                    String x = p1.trim();
                    String p1ok = x.substring(1, x.length() - 1);
                    p2 = parts[1];
                    String f = p2.trim();
                    String p2ok = f.substring(1, f.length() - 2);

                    if (p1ok.equalsIgnoreCase("nombre")) {
                        miSkin.setNombre(p2ok);
                        System.out.println("imprimir el nommmmmbrrerrrerer: " + p2ok);
                    } else if (p1ok.equalsIgnoreCase("configuracion")) {
                        Boolean a = Boolean.parseBoolean(p2ok);
                        System.out.println("Imprimir la configuracioooooon:" + a);
                        miSkin.setConfiguracion(a);
                    } else if (p1ok.equalsIgnoreCase("color")) {
                        miSkin.setColor(p2ok);
                        System.out.println("Imprimir el colorrrorororororororo:" + p2ok);

                    } else if (p1ok.equalsIgnoreCase("precio")) {
                        Double precio = Double.parseDouble(p2);
                        miSkin.setPrecio(precio);
                    }
                    if ((miSkin.getNombre() != null) && (miSkin.getPrecio() != null) && (miSkin.getColor() != null)) {
                        listadoSkins.add(miSkin);
                        System.out.println("Añadimos Skin a la lista de ssssssssssssssssssskkkkkkkkkkkkkkkkkkkkkkkkiiiinnnnss");
//                        miSkin.setNombre(null);
//                        miSkin.setConfiguracion(null);
//                        miSkin.setColor(null);
//                        miSkin.setPrecio(null);
                    }
                }
            }
            i++;
            // System.out.println("El skin obtenido es------------------" + miSkin);
        }
        for (Skin e : listadoSkins) {
            System.out.println("Elementos de la lista de skinnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnssssssssssss" + e);
        }
        return listadoSkins;
    }

    public static List<String> lecturaFichero() {
        List<String> listaRecuperada = new ArrayList<>();
        try {
            FileReader entrada = new FileReader(".\\src\\main\\resources\\data\\skins.json");//Ruta relativa

            BufferedReader miBuffer = new BufferedReader(entrada);
            String linea = "";
            while (linea != null) {
                linea = miBuffer.readLine();
                listaRecuperada.add(linea);
            }
            entrada.close();
            miBuffer.close();
            for (String e : listaRecuperada) {
                System.out.println(e);
            }
            return listaRecuperada;


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Skin> buscarSkinByName(Skin skin) {
        Optional<Skin> skinOptional = skinRepositori.buscarSkinByName(skin.getNombre());
        if (skinOptional.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se ha encontaddo el skin");
        }
        if (skin.getNombre() == null) {
            throw new NotFoundException("p-505", HttpStatus.BAD_REQUEST, "falta introducir el nombre del skin buscado");
        }
        return skinOptional;
    }

    @Override
    public List<Skin> listarSkins() {
        return skinRepositori.findAll();
    }

    @Override
    public Skin obtenerSkinById(Long skin_id) {
        Optional<Skin> skinOptional = skinRepositori.findById(skin_id);
        if (skinOptional.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se ha encontrado el skin buscado");
        }
        if (skin_id == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
        if (!(skin_id instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "Formato campo ID incorrecto");
        }
        return skinOptional.get();
    }

    @Override
    public Skin actualizarSkin(Skin skin, Long usuario_id) {
        Optional<Usuario> usuarioOptional = usuarioRepositori.findById(usuario_id);
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se ha encontrado al usuario");
        }
        if (usuario_id == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
        if (!(usuario_id instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "Formato campo ID incorrecto");
        }
        List<Skin> listadoSkins = usuarioOptional.get().getSkins();
        if (listadoSkins.isEmpty()) {
            throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "Este usuario no ha comprado ningun skin");
        }

        boolean a=false;
        for (Skin ele: listadoSkins) {
            if(ele.getId()== skin.getId()){
                a=true;
            }
        }
        Optional<Skin> skinOptional = skinRepositori.findById(skin.getId());
        if (skin.getId() == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
        if (!(skin.getId() instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "Formato campo ID incorrecto");
        }
        if(a){
            skinOptional.get().setColor(skin.getColor());
            return skinRepositori.save(skinOptional.get());
        }else{
            throw new NotFoundException("p-404",HttpStatus.NOT_FOUND,"No se ha encontrado el Skin a actualizar");
        }
    }

    @Override
    public Skin comprarSkin(Skin skin, Long usuario_id) {
        //Aqui debería buscar en el listado de skins disponibles, pero no he sabido como crear un List<Skins>
        //a partir del archivo json, por lo tanto creamos los Skins desde postman con el metodo save
        if (usuario_id == null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
        if (!(usuario_id instanceof Long)) {
            throw new BadRequestExceptionV2("p-503", HttpStatus.BAD_REQUEST, "Formato campo ID incorrecto");
        }
        if (skin.getId() != null) {
            throw new BadRequestExceptionV2("p-502", HttpStatus.BAD_REQUEST, "Falta campo ID");
        }
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
                    throw new NotFoundException("p-302", HttpStatus.CONFLICT, "El usuario no tiene saldo suficiente para hacer la compra");
                }

            } else {
                throw new NotFoundException("p-404", HttpStatus.NOT_FOUND, "No se ha encontrado al usuario");
            }
        } else {
            throw new NotFoundException("p-404", HttpStatus.NO_CONTENT, "El skin no esta disponible");
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

