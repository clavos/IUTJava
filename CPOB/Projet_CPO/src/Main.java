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
		Interface fenetre = new Interface(); // On initialise l'interface
		OutilsJDBC sql = new OutilsJDBC(); // On initialise la connexion à la BD ( jdbc:mysql://localhost:3306/projet_cpo )
		fenetre.set();

	}

}
