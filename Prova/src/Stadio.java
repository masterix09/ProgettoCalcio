
public class Stadio {

	private String Via;
	private int Civico;
	private int Capienza;
	private String Nome;
	
	
	public Stadio(String Road, int Number, int capacity, String Name) {
		
		this.setVia(Road);
		this.setCivico(Number);
		this.setCapienza(capacity);
		this.setNome(Name);
	}
	
	
	public Stadio() {
		
	}
	
	public String getVia() {
		return Via;
	}
	public void setVia(String via) {
		Via = via;
	}
	public int getCivico() {
		return Civico;
	}
	public void setCivico(int civico) {
		Civico = civico;
	}
	public int getCapienza() {
		return Capienza;
	}
	public void setCapienza(int capienza) {
		Capienza = capienza;
	}


	public String getNome() {
		return Nome;
	}


	public void setNome(String nome) {
		Nome = nome;
	}
	
	
	
}
