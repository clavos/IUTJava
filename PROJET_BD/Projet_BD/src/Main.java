import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * CART Robin
 * MARTIN Killian
 * MORCRETTE Alexandre
 * Groupe 3D2
 */


public class Main {

	public static void main(String[] args) {
		OutilsJDBC sql = new OutilsJDBC(); // On initialise la connexion � la BD 
		Interface fenetre = new Interface(); // On initialise l'interface
		fenetre.set();

	}

}
