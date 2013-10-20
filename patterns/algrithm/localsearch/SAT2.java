package localsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SAT2 {
	private int N_Clause = 0;
	private Map<Integer,Var> varMap = new HashMap <Integer,Var>();
	private List<Clause> clauseList = new LinkedList<Clause>();
	
	private Random random = new Random();
	
	public SAT2(List<String> lineList){
		N_Clause = Integer.valueOf(lineList.get(0).trim());
		for(int i = 0;i<N_Clause;i++){
			String[] strPair = lineList.get(i+1).trim().split(" ");
			int v1 = Integer.valueOf(strPair[0]);
			int v2 = Integer.valueOf(strPair[1]);
			clauseList.add( new Clause(v1, v2));
		}
		int n = reduction();
		while(n<N_Clause){
			N_Clause = n;
			n = reduction();
		}

		int N_Var = varMap.values().size();
		System.out.println(N_Clause+" clause, "+N_Var +" variables");
	}
	
	private int reduction(){
		Iterator<Var> itVar = varMap.values().iterator();
		while(itVar.hasNext()){
			Var var = itVar.next();
			if(var==null||!var.isFixed()){
				var.reset();
				continue;
			}
			var.markClauseAsToBeRemoved();
			itVar.remove();
		}

		Iterator<Clause> it = clauseList.iterator();
		while(it.hasNext()){
			Clause c = it.next();
			if(c.toBeRemoved()){
				it.remove();
			}
		}
				
		for(Clause c:clauseList){
			Var v1 = c.var1;
			Var v2 = c.var2;
			if(c.negative1)
				v1.setNegativeflag(true);
			else
				v1.setPositiveflag(true);
			
			if(c.negative2)
				v2.setNegativeflag(true);
			else
				v2.setPositiveflag(true);
		}
		return clauseList.size();
	}
	
	public int calculate(){
		if(N_Clause<3){
			return 1;
		}
		int n = (int) (Math.log(N_Clause)/Math.log(2));
		int n_2 = (int) (2*Math.pow(N_Clause, 2));
//		int n =1, n_2 = 1;
		for(int i=0;i<n;i++){
			initAssignment();
			for(int j=0;j<n_2;j++){
				int k = isAllClauseSatisfied();
				if(k==N_Clause){
					return 1;
				}else{
					clauseList.get(k).flip();
				}
			}
		}
		return 0;
	}
	
	private void initAssignment(){
		for(Var var: varMap.values()){
			var.setValue(random.nextBoolean());
		}
	}
	
	private int isAllClauseSatisfied(){
		int i = 0;
		List<Integer> unsatisfiedlist = new ArrayList<Integer>();
		for( ;i<N_Clause;i++){
			Clause c = clauseList.get(i);
			if(!c.isSatisfied())
				unsatisfiedlist.add(i);
		}
		int n = unsatisfiedlist.size();
		if(n>0)
			return unsatisfiedlist.get(random.nextInt(n));
		else 
			return N_Clause;
	}
	
	class Clause{
		Var var1;
		boolean negative1 = false;
		Var var2;
		boolean negative2 = false;
		boolean toBeRemoved = false;
		public Clause(int v1, int v2){
			int id1 = v1, id2=v2;
			if(v1<0){
				negative1 = true;
				id1 = -v1;
			}
			if(v2<0){
				negative2 = true;
				id2 = -v2;
			}
			var1 = varMap.get(id1);
			if(var1==null){
				var1 = new Var(v1);
				varMap.put(id1, var1);
			}else{
				if(v1>0)
					var1.setPositiveflag(true);
				else if(v1<0){
					var1.setNegativeflag(true);
				}
			}
			var1.addRelativeClause(this);
			
			var2 = varMap.get(id2);
			if(var2==null){
				var2 = new Var(v2);
				varMap.put(id2, var2);
			}else{
				if(v2>0)
					var2.setPositiveflag(true);
				else if(v2<0){
					var2.setNegativeflag(true);
				}
			}
			var2.addRelativeClause(this);
		}
		
		public boolean toBeRemoved(){
			return toBeRemoved;
		}
		
		public void setToBeRemoved(){
			toBeRemoved = true;
		}
		
		public boolean isSatisfied(){
			boolean x1 = negative1?!var1.getValue():var1.getValue();
			if(x1)
				return true;
			boolean x2 = negative2?!var2.getValue():var2.getValue();
			return x2;
		}
		
		public void flip(){
			if(random.nextBoolean())
				var1.setValue(!var1.getValue());
			else
				var2.setValue(!var2.getValue());
		}
		
		public String toString(){
			return (negative1?"[~(":"[")+var1.toString()+(negative2?"),~(":"),(")+var2.toString()+"): " +
					isSatisfied()+(toBeRemoved?",Remove]":"]");
		}
	}
	
	class Var{
		int id;
		boolean value = false;
		boolean negativeflag=false;
		boolean positiveflag = false;
		List<Clause> clauseList = new ArrayList<Clause>();
		public Var(int v){
			if(v>=0){
				id = v;
				positiveflag = true;
			}
			else{
				id = -v;
				negativeflag = true;
			}
		}
		
		public boolean isFixed() {
			return !(negativeflag&&positiveflag);
		}

		public void setNegativeflag(boolean negativeflag) {
			this.negativeflag = negativeflag;
		}

		public void setPositiveflag(boolean positiveflag) {
			this.positiveflag = positiveflag;
		}
		
		public void reset(){
			negativeflag = false;
			positiveflag = false;
		}

		public void addRelativeClause(Clause c){
			clauseList.add(c);
		}
		
		public void markClauseAsToBeRemoved(){
			for(Clause c:clauseList){
				c.setToBeRemoved();
			}
		}
		
		public void setValue(boolean value){
			this.value = value;
		}
		
		public boolean getValue(){
			return value;
		}
		
		public String toString(){
			return String.valueOf(id)+"="+String.valueOf(value);
		}
		
	}

}
