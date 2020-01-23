
public class Persona {
	
	private String Nome;
	private String Cognome;
	private String Cod_Fiscale;
	
	
	public Persona(String Name, String Surname, String CodFisc) {
		
		this.setNome(Name);
		this.setCognome(Surname);
		this.setCod_Fiscale(CodFisc);
		
	}
	
	
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getCod_Fiscale() {
		return Cod_Fiscale;
	}
	public void setCod_Fiscale(String cod_Fiscale) {
		Cod_Fiscale = cod_Fiscale;
	}
	
	
	
	

}
