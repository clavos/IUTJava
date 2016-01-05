import java.sql.*;

public class OutilsJDBC {
	static Connection co = null;

	public static Connection openConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			co = DriverManager.getConnection(
					"jdbc:oracle:thin:amorcre/amorcre@oracle.iut-orsay.fr:1521:etudom");

		} catch (ClassNotFoundException e) {
			System.out.println("il manque le driver oracle");
			System.exit(1);
		} catch (SQLException e) {
			System.out
					.println("impossible de se connecter � l'url : jdbc:oracle:thin:###/###@oracle.iut-orsay.fr:1521:etudom");
			System.exit(1);
		}
		return co;
	}

	public static ResultSet exec1Requete(String requete, Connection co, int type) {
		ResultSet res = null;
		try {
			Statement st;
			if (type == 0) {
				st = co.createStatement();
			} else {
				st = co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
			}
			;
			res = st.executeQuery(requete);
		} catch (SQLException e) {
			System.out.println("Probl�me lors de l'ex�cution de la requete : "
					+ requete);
		}
		;
		return res;
	}

	public static void closeConnection() {
		try {
			co.close();
			System.out.println("Connexion ferm�e!");
		} catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}
	}

	OutilsJDBC() {

		Connection co = OutilsJDBC.openConnection();
		System.out.println("connection ouverte");

	}

	// On importe le nombre d'�tudiant
	public static void nbEtudiantR() {
		String requete = "SELECT COUNT(*) AS nb FROM ob_etudiant";
		int res = 0;
		try {
			ResultSet resultat = exec1Requete(requete, co, 0);
			while (resultat.next()) {
				res = resultat.getInt("nb");
			}
			
			Interface.stage.nbEtudiant =res;
		} catch (SQLException e) {
			System.out.println("Erreur: "+requete);
		}
	}

	// On importe le nombre d'�tudiant avec un stage
	public static void nbStage() {
		String requete = "SELECT COUNT(*) AS nb FROM ob_etudiant where numStage IS NOT NULL";
		int res = 0;
		try {
			ResultSet resultat = exec1Requete(requete, co, 0);
			while (resultat.next()) {
				res = resultat.getInt("nb");
			}
			
			Interface.stage.nbStage =res;
		} catch (SQLException e) {
			System.out.println("Erreur: "+requete);
		}
	}
	
	// Calcul le nombre d'�tudiant sans stage
	public static void nbNonStage() {
		Interface.stage.nbNonStage = Interface.stage.nbEtudiant - Interface.stage.nbStage;
		
	}

	// Importe le nombre d'�tudiant sans stage � une date donn�e
	public static int nbNonStageD(String ann�e) {
		String requete = "select count (*) AS nb FROM ob_etudiant o where o.numStage IS NOT NULL AND o.numStage.dateS>=TO_DATE('01/01/"+ann�e+"', 'DD/MM/YYYY') AND o.numStage.dateS<=TO_DATE('31/12/"+ann�e+"', 'DD/MM/YYYY')";

		int res = 0;
		try {
			ResultSet resultat = exec1Requete(requete, co, 0);
			while (resultat.next()) {
				res = resultat.getInt("nb");
			}
			
			Interface.stage.nbStage =res;
		} catch (SQLException e) {
			System.out.println("Erreur: "+requete);
		}
		
		return res;
	}
	
	// Importe le nombre de stage par Entreprise depuis n ann�e
	public static void nbStageEntr(int n, String entr) {
		String requete = "select count (*) AS nb FROM ob_etudiant o where o.numStage IS NOT NULL AND o.numStage.dateS>=TO_DATE('01/01/"+(2016-n)+"', 'DD/MM/YYYY') and o.numStage.numEntreprise.nomEntreprise = '"+entr+"'";

		int res = 0;
		try {
			ResultSet resultat = exec1Requete(requete, co, 0);
			while (resultat.next()) {
				res = resultat.getInt("nb");
			}
			
			Interface.stage.nbStageEntre =res;
		} catch (SQLException e) {
			System.out.println("Erreur: "+requete);
		}
		
	}
	
	// Importe le nombre de stage MOYEN depuis n ann�es
	public static void nbStageMoyen(int n) {
		String requete = "select count (*) AS nb FROM ob_etudiant o where o.numStage IS NOT NULL AND o.numStage.dateS>=TO_DATE('01/01/"+(2016-n)+"', 'DD/MM/YYYY')";

		int res = 0;
		try {
			ResultSet resultat = exec1Requete(requete, co, 0);
			while (resultat.next()) {
				res = resultat.getInt("nb");
			}
			
			Interface.stage.nbMoyenne =res;
		} catch (SQLException e) {
			System.out.println("Erreur: "+requete);
		}
		
	}
	
	// Importe le nombre de stage par ville
	public static void nbStageVille(String ville) {
		String requete = "select count (*) AS nb FROM ob_etudiant o where o.numStage IS NOT NULL AND o.numStage.numEntreprise.ville='"+ville+"'";

		int res = 0;
		try {
			ResultSet resultat = exec1Requete(requete, co, 0);
			while (resultat.next()) {
				res = resultat.getInt("nb");
			}
			
			Interface.stage.nbVille =res;
		} catch (SQLException e) {
			System.out.println("Erreur: "+requete);
		}
		
	}
	
	// Rempli le tableau de villes
	public static void ville() {
		String requete = "select DISTINCT ville FROM ob_entreprise";
		int nb =0;
	
		try {
			ResultSet resultat = exec1Requete(requete, co, 0);
			while (resultat.next()) {
				 Interface.tableauVille[nb] = resultat.getString("ville");
				 nb++;
			}
			Interface.stage.nbVille =nb;
		} catch (SQLException e) {
			System.out.println("Erreur: "+requete);
		}
	}
	
	// Rempli le tableau d'entreprises
	public static void entreprise() {
		String requete = "select DISTINCT nomEntreprise FROM ob_entreprise";
		int nb =0;
	
		try {
			ResultSet resultat = exec1Requete(requete, co, 0);
			while (resultat.next()) {
				 Interface.tableauEntre[nb] = resultat.getString("nomEntreprise");
				 nb++;
			}
			Interface.stage.nbEntre =nb;
		} catch (SQLException e) {
			System.out.println("Erreur: "+requete);
		}
		
	}
	
	

}
