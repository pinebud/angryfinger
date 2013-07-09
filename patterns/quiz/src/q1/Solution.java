package q1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

	/**
	 * 
	 * @param inputs
	 * @return outputs
	 */
	public List<Boolean> matchRule(List<String> baseRuleList, List<String> TobeVerifiedRuleList){
		List<Boolean> outputs = new ArrayList<Boolean>();
		RelationMatrix rMatrix = new RelationMatrix(baseRuleList);
		for(String rule:TobeVerifiedRuleList){
			outputs.add(rMatrix.validate(rule));
		}
		return outputs;
	}
	
	class RelationMatrix{
		List<Character> charList = new ArrayList<Character>();
		List<Reachable> reachableList = new ArrayList<Reachable>();
		public RelationMatrix(List<String> baseRuleList){
			for(String baseRule: baseRuleList){
				List<Reachable> list = parse(baseRule);
				addReachables(list);
			}
		}
		
		public boolean isReachable(Reachable reachable){
			for(Reachable r: reachableList){				
				if(r.src==reachable.src&&r.target==reachable.target){
					return true;
				}
			}			
			return false;
		}
		
		public boolean isReachable(List<Reachable> validationList){
			for(Reachable v: validationList){
				if(!isReachable(v)){
					return false;
				}
			}
			return true;
		}
		
		private void addReachables(List<Reachable> newReachables){
			for(Reachable r: newReachables){
				if(!isReachable(r)){
					if(!containChar(r.src)){
						charList.add(r.src);
					}
					if(!containChar(r.target)){
						charList.add(r.target);
					}
					reachableList.add(r);
					scrubReachableList();
				}
			}
		}
		
		private void scrubReachableList(){
			Stack<Character> stack = new Stack<Character>();
			for(char x:charList){
				for(char y:charList){
					if(x!=y&!isReachable(new Reachable(x,y))){
						stack.empty();
						stack.push(x);
						boolean isFound = false;
						while(!stack.empty()&&!isFound){
							char top = stack.pop();
							for(char ch: getReachableChars(top)){
							if(ch==y){
								reachableList.add(new Reachable(x, y));
								isFound = true;
								break;
							}
							else{
								stack.push(ch);
							}
							}
						}
					}
				}
			}
		}
		
		private List<Character> getReachableChars(char x){
			List<Character> list = new ArrayList<Character>();
			for(Reachable r:reachableList){
				if(r.src==x){
					list.add(r.target);
				}
			}
			return list;
		}
		private List<Reachable> parse(String input){
			int pos = input.indexOf(">");
			if(pos==-1)
				return null;
			String left = input.substring(0, pos);
			String right = input.substring(pos+1);
			char t = right.charAt(0);		
			List<Reachable> list = new ArrayList<Reachable>();
			String[] strs = left.split(",");
			for(String s : strs){
				char c = s.trim().charAt(0);
				Reachable reachable = new Reachable(c, t);
				list.add(reachable);
			}
			return list;
		}
		private boolean containChar(char ch){
			for(char c: charList){
				if(c==ch){
					return true;
				}
			}
			return false;
		}
		
		public boolean validate(String rule) {
			return isReachable(parse(rule));
		}
		
	}
	
	class Reachable{
		char src;
		char target;
		public Reachable(char src, char target){
			this.src = src;
			this.target = target;
		}	
	}

}
