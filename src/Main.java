import java.security.GeneralSecurityException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main {

	public static void main(String[] args) {
		
		 Scanner sn = new Scanner(System.in);
		 boolean login = true;
		 int attempts = 3;
		 boolean exit = false;
		 int option;
		 String phrase = "";
		 byte[] bytesOriginalPhrase = null;
		 byte[] bytesCipherPhrase = null;	 
		 byte[] bytesDecipherPhrase = null;	 
		
		 
			 System.out.println("SISTEMA DE ENCRIPTACIÓN SIMÉTRICO");
			 
			 while (login) {
				 System.out.println("Usuario:");
				 String username = sn.next();
				 System.out.println("Contraseña:");
				 String password= sn.next();
				 if (attempts !=0){
					 if (username.equals("user") && password.equals("asd123")){
						 System.out.println("Bienvenido " + username);
						 login = false;
						 exit = false;
					}else{
						System.out.println("El nombre de usuario y/o contraseña es incorrecto, vuelva a intentarlo.");
						attempts--;
						exit = true;
						}
				}else{
					System.out.println("Ha superado el número de intentos permitidos. Inténtelo de nuevo más tarde.");
					exit = true;
					login = false;
					}
			 }


			while (!exit) {
				
				System.out.println("MENÚ");
				System.out.println("0. Salir del Programa");
				System.out.println("1. Encriptar frase");
				System.out.println("2. Desencriptar frase");
				System.out.println("Seleccione una opción:");
			
			try {
				
				KeyGenerator generator = KeyGenerator.getInstance("AES");
				SecretKey key = generator.generateKey();
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, key);
				Cipher decipher = Cipher.getInstance("AES");
				decipher.init(Cipher.DECRYPT_MODE, key);
				
				option = sn.nextInt();
				switch(option) {
				
				case 0: 
					System.out.println("Cerrando aplicación...");
					System.out.println("Aplicación cerrada.");
					exit = true;
					break;
				
				case 1:
					System.out.println("Introduzca una frase para encriptar:");
					phrase = sn.next();
					bytesOriginalPhrase = phrase.getBytes();
					bytesCipherPhrase = cipher.doFinal(bytesOriginalPhrase);
					System.out.println("Frase encriptada:" + new String(bytesCipherPhrase));
					break;
					
				case 2:
					System.out.println("Frase encriptada:" + new String(bytesCipherPhrase));
					bytesDecipherPhrase = decipher.doFinal(bytesCipherPhrase);
					System.out.println("Frase desencriptada:" + new String(bytesDecipherPhrase));
					break;
					
				default:
					System.out.println("Las opciones son entre 0 y 2.");
					break;
					
				}
				
			}catch(InputMismatchException e) {
				System.out.println("Debes introducir un número entre 0 y 2.");
				sn.next();
			}catch (GeneralSecurityException e){
				System.out.println("Se ha producido un error: " + e);
				sn.next();
			}
		} 
	}
}

