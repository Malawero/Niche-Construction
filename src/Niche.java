/*
	Reproduction of the original experiment. Consisting of the next classes
	- Niche
	- Population
	- Genotype
	- Model
	
	I declare that all code was written by myself
 */
import java.util.ArrayList;
import java.util.Collections;

public class Niche {
	public Population total;
	
	public ArrayList<Population> groups; 
	
	public static void main(String[] args) {
		Niche niche = new Niche();
		
		for(int i = 0; i < Model.T; i++) {
			niche.generation();
		}
	}
	
	public Niche() {
		total = new Population(Model.N/4, Model.N/4, Model.N/4, Model.N/4);
	}
	
	public void generation() {
		this.total.print();
		this.aggregation(this.total);
		this.reproduction();
		this.dispersal();
	}
	
	public void aggregation(Population pop) {
		groups = new ArrayList<Population>();
				
		ArrayList<Genotype> sub = new ArrayList<Genotype>();
		sub.addAll(pop.iCS);
		sub.addAll(pop.iSS);
		Collections.shuffle(sub);
		
		while( sub.size() >= Model.SMALL) {
			groups.add(new Population(sub, Model.SMALL, Model.RS));
		}
		
		sub = new ArrayList<Genotype>();
		sub.addAll(pop.iCL);
		sub.addAll(pop.iSL);
		Collections.shuffle(sub);
		
		while( sub.size() >= Model.LARGE) {
			groups.add(new Population(sub, Model.LARGE, Model.RL));
		}
	}//aggregation
	
	public void reproduction() {
		for(Population pop : this.groups) {
			pop.reproduction(Model.t);
		}
	}
	
	public void dispersal() {
		double cs = 0;
		double cl = 0;
		double ss = 0;
		double sl = 0;
		
		for(Population pop : this.groups) {
			cs = cs + pop.coopSmall.frequency;
			cl = cl + pop.coopLarge.frequency;
			ss = ss + pop.selfSmall.frequency;
			sl = sl + pop.selfLarge.frequency;
			pop = null;
		}
		
		double sum = cs + cl + ss + sl;
		cs = (Model.N * cs)/sum;
		cl = (Model.N * cl)/sum;
		ss = (Model.N * ss)/sum;
		sl = (Model.N * sl)/sum;
		
		this.groups = null;
		this.total  = null;
		this.total  = new Population(cs,cl,ss,sl);
	}
	
	public void printGroups() {
		for(Population pop : this.groups) {
			pop.print();
		}
	}
}
