
public class Giocatore {

	private int NMaglia;
	private String Ruolo;
	
	public Giocatore(int Number, String Position) {
		
		this.setNMaglia(Number);
		this.setRuolo(Position);
		
	}

	public int getNMaglia() {
		return NMaglia;
	}

	public void setNMaglia(int nMaglia) {
		NMaglia = nMaglia;
	}

	public String getRuolo() {
		return Ruolo;
	}

	public void setRuolo(String ruolo) {
		Ruolo = ruolo;
	}
	
}
