import java.util.ArrayList;

public class Population {
	public Genotype coopSmall;
	public Genotype coopLarge;
	public Genotype selfSmall;
	public Genotype selfLarge;
	
	public double R;
	
	ArrayList<Genotype> genotypes;
	ArrayList<Genotype> iCS;
	ArrayList<Genotype> iCL;
	ArrayList<Genotype> iSS;
	ArrayList<Genotype> iSL;
	
	public Population(double cs, double cl, double ss, double sl) {
		this.coopSmall = new Genotype(true,  true,  cs);
		this.coopLarge = new Genotype(true,  false, cl);
		this.selfSmall = new Genotype(false, true,  ss);
		this.selfLarge = new Genotype(false, false, sl);
				
		this.iCS = this.sub(cs, true, true);
		this.iCL = this.sub(cl, true, false);
		this.iSS = this.sub(ss, false, true);
		this.iSL = this.sub(sl, false, false);
	}//Population(freqs)
	
	public ArrayList<Genotype> sub(double size, boolean coop, boolean small) {
		ArrayList<Genotype> sub = new ArrayList<Genotype>();
		
		size = Math.rint(size);
		
		for(double i = 0; i <= size; i++) {
			sub.add(new Genotype(coop,small));
		}
		
		return sub;
	}//sub
	
	public Population(ArrayList<Genotype> genotypes, int size, double resource) {
		this.coopSmall = new Genotype(true,  true);
		this.coopLarge = new Genotype(true,  false);
		this.selfSmall = new Genotype(false, true);
		this.selfLarge = new Genotype(false, false);
		
		this.R = resource;
		
		this.iCS = new ArrayList<Genotype>();
		this.iCL = new ArrayList<Genotype>();
		this.iSS = new ArrayList<Genotype>();
		this.iSL = new ArrayList<Genotype>();
		
		for(int i = 0; i < size; i++) {
			Genotype genotype = genotypes.remove(0);
			
			if(genotype.coop){
				if(genotype.small) {
					this.coopSmall.frequency++;
					this.iCS.add(genotype);
				}else {
					this.coopLarge.frequency++;
					this.iCL.add(genotype);
				}
			}else {
				if(genotype.small) {
					this.selfSmall.frequency++;
					this.iSS.add(genotype);
				}else {
					this.selfLarge.frequency++;
					this.iSL.add(genotype);
				}
			}
		}
	}//Population(individuals, size)
	
	public void reproduction(int timeSteps) {
		for(int i = 0; i < timeSteps; i++) {
			this.reproduction();
		}
	}
	
	public void reproduction() {
		double rcs = this.coopSmall.share();
		double rcl = this.coopLarge.share();
		double rss = this.selfSmall.share();
		double rsl = this.selfLarge.share();
		
		double sum = rcs + rcl + rss + rsl;
		
		rcs = (rcs / sum) * this.R ;
		rcl = (rcl / sum) * this.R ;
		rss = (rss / sum) * this.R ;
		rsl = (rsl / sum) * this.R ;
		
		this.coopSmall.reproduce(rcs);
		this.coopLarge.reproduce(rcl);
		this.selfSmall.reproduce(rss);
		this.selfLarge.reproduce(rsl);
		
//		this.R = this.R - (rcs + rcl + rss + rsl);
	}
	
	public void print() {
		System.out.println(this.coopSmall.getFreq()+","+
                           this.coopLarge.getFreq()+","+
				           this.selfSmall.getFreq()+","+
				           this.selfLarge.getFreq()+",");
		
//		System.out.println(this.coopSmall.frequency+","+
//	                       this.coopLarge.frequency+","+
//				           this.selfSmall.frequency+","+
//				           this.selfLarge.frequency+",");
	}
}
