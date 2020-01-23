
public class Goal {

	private int NMaglia;
	private int ora;
	
	public Goal(int Number, int time) {
		
		this.setNMaglia(Number);
		this.setOra(time);
		
		
	}
	
	public int getNMaglia() {
		return NMaglia;
	}
	public void setNMaglia(int nMaglia) {
		NMaglia = nMaglia;
	}
	public int getOra() {
		return ora;
	}
	public void setOra(int ora) {
		this.ora = ora;
	}
	
	
}
