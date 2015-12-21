import java.sql.*;

public class OutilsJDBC {
	static Connection co = null;

	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/projet_cpo", "root", "");

		} catch (ClassNotFoundException e) {
			System.out.println("il manque le driver oracle");
			System.exit(1);
		} catch (SQLException e) {
			System.out
					.println("impossible de se connecter � l'url : jdbc:mysql://localhost:3306/projet_cpo");
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

	// On insere ou met � jour la soutenance li� � la session
	public static void insererSoutenance(Session session, String periode,String jour, String mois) {
		String requete = "SELECT COUNT(*) AS nb FROM soutenance WHERE numCompte ='"+ session.numCompte + "'";
		int res = 0;
		try {
			ResultSet resultat = exec1Requete(requete, co, 1);
			while (resultat.next()) {
				res = resultat.getInt("nb");
			}
			if (res == 1) {
				requete = "UPDATE soutenance SET periode ='" + periode+ "', jour ='" + jour + "', mois ='" + mois+ "' WHERE numCompte ='" + session.numCompte + "'";

			} else {
				requete = "INSERT INTO soutenance VALUES ('" + periode + "',"+ session.numCompte + ",'" + jour + "','" + mois+ "');";

			}
			Statement stat = co.createStatement();
			stat.executeUpdate(requete);
			session.soutenance.periode =periode;
			session.soutenance.jour =jour;
			session.soutenance.mois =mois;

		} catch (SQLException e) {
			System.out.println("Inserer Soutenance");
		}
	}

	// Un utilisateur se connecte � sa session
	public static void login(Session session) {
		boolean verif = false;
		int res = 0;
		String requete = "SELECT COUNT(*) AS total FROM compte WHERE login='"+ session.login + "' AND mdp='" + session.mdp + "'";
		String requete2 = "SELECT numCompte FROM compte WHERE login='"+ session.login + "' AND mdp='" + session.mdp + "'";

		try {
			ResultSet resultat = exec1Requete(requete, co, 1);
			while (resultat.next()) {
				if (resultat.getInt("total") == 0) {
					verif = false;
				}
				if (resultat.getInt("total") == 1) {
					verif = true;
				}
			}

			if (verif) {

				resultat = exec1Requete(requete2, co, 1);
				while (resultat.next()) {
					res = resultat.getInt("numCompte");
				}
			}
		} catch (SQLException e) {
			System.out.println("login");
		}
		session.numCompte = res;
		initiate(session);

	}

	private static void initiate(Session session) {
		String req = "SELECT COUNT(*) AS nb FROM soutenance WHERE numCompte ='"+ session.numCompte + "'";
		String requete = "SELECT periode FROM soutenance WHERE numCompte='"+ session.numCompte + "'";
		String requete2 = "SELECT jour FROM soutenance WHERE numCompte='"+ session.numCompte + "'";
		String requete3 = "SELECT mois FROM soutenance WHERE numCompte='"+ session.numCompte + "'";
		int res = 0;

		try {
			ResultSet resultat = exec1Requete(req, co, 1);
			while (resultat.next()) {
				res = resultat.getInt("nb");
			}

			if (res == 1) {
				resultat = exec1Requete(requete, co, 1);
				while (resultat.next()) {
					session.soutenance.periode = resultat.getString("periode");
				}
				resultat = exec1Requete(requete2, co, 1);
				while (resultat.next()) {
					session.soutenance.jour = resultat.getString("jour");
				}
				resultat = exec1Requete(requete3, co, 1);
				while (resultat.next()) {
					session.soutenance.mois = resultat.getString("mois");
				}
			}

		} catch (SQLException e) {
			System.out.println("initiate");
		}
	}

}
