public class Soutenance {
	String periode;
	String jour;
	String mois;

	Soutenance() {
		this.periode = "Aucune";
		this.jour = " soutenance";
		this.mois = " enregistr� !";
	}

	Soutenance(String periode, String jour, String mois) {
		this.periode = periode;
		this.jour = jour;
		this.mois = mois;
	}
	

}