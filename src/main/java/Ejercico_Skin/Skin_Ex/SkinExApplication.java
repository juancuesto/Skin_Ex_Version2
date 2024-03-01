package Ejercico_Skin.Skin_Ex;

import Ejercico_Skin.Skin_Ex.repository.UsuarioRepositori;
import Ejercico_Skin.Skin_Ex.service.SkinServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SkinExApplication {

	public static void main(String[] args) {

		SpringApplication.run(SkinExApplication.class, args);
//		ApplicationContext context=SpringApplication.run(SkinExApplication.class, args);
//
//		SkinServiceImpl impl=context.getBean(SkinServiceImpl.class);
//
//		List<String> listA=impl.lecturaFichero();
	}



}
