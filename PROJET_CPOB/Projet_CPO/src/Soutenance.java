public class Soutenance {
	String periode;
	String jour;
	String mois;

	Soutenance() {
		this.periode = "Aucune";
		this.jour = " soutenance";
		this.mois = " enregistré !";
	}

	Soutenance(String periode, String jour, String mois) {
		this.periode = periode;
		this.jour = jour;
		this.mois = mois;
	}
	

}
