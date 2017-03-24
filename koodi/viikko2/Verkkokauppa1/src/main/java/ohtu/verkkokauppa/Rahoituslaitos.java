package ohtu.verkkokauppa;

public interface Rahoituslaitos {
	public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
