
public class Genotype {
	public static final char COOP  = 'C';
	public static final char SELF  = 'S';
	
	public static final int  SMALL = 4;
	public static final int  LARGE = 40;
	
	public boolean coop;
	public boolean small;
	
	public double frequency;
	public double growth;
	public double consume;
	
	public void initialise(boolean coop, boolean small) {
		this.coop  = coop;
		this.small = small;
		
		this.frequency = 0;
		
		if(this.coop) {
			this.growth  = Model.GC;
			this.consume = Model.CC;
		}else {
			this.growth  = Model.GS;
			this.consume = Model.CS;
		}
	}
	
	public Genotype(boolean coop, boolean small) {
		this.initialise(coop,small);
	}
	
	public Genotype(boolean coop, boolean small, double freq) {
		this.initialise(coop,small);
		this.frequency = freq;
	}
	
	public double share() {
		return this.frequency * this.growth * this.consume;
	}
	
	public void reproduce(double share){
		this.frequency = this.frequency + (share/this.consume) - (Model.K * this.frequency);
//		this.frequency = Math.rint(this.frequency);
	}
	
	public double getFreq() {
		return this.frequency/Model.N;
	}
}
