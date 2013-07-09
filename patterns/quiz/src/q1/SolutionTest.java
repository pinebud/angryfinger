package q1;

import java.util.ArrayList;
import java.util.List;


import org.junit.Assert;
import org.junit.Test;


/**
 * 算法复杂度：
 * n表示rule当中left element的个数
 * m表示rule的个数
 * l表示图中点的个数
 * k表示图中边的个数
 * 
 * 建立矩阵：n*m*l*l*k
 * 验证：n*m*k (通过使用map，应该可以使复杂度变为n*m)
 * @author 牛P
 *
 */
public class SolutionTest {
	
	@Test
	public void testMatchRule_NormalCase0(){
		Solution solution = new Solution();
		List<String> baseRuleList = new ArrayList<String>();
		baseRuleList.add("A>B");
		baseRuleList.add("B>C");
		List<String> ruleList = new ArrayList<String>();
		ruleList.add("A>C");	
		List<Boolean> outputList = solution.matchRule(baseRuleList, ruleList);
		Assert.assertTrue(outputList.get(0));
	}
	
	@Test
	public void testMatchRule_NormalCase1(){
		Solution solution = new Solution();
		List<String> baseRuleList = new ArrayList<String>();
		baseRuleList.add("A,B>C");
		baseRuleList.add("D,E>A");
		baseRuleList.add("E>B");
		List<String> ruleList = new ArrayList<String>();
		ruleList.add("E,D>C");
		ruleList.add("F,E>C");	
		List<Boolean> outputList = solution.matchRule(baseRuleList, ruleList);
		Assert.assertTrue(outputList.get(0));
		Assert.assertFalse(outputList.get(1));
	}
	
	/**
	 * If the program don't take consideration for the loop when traversing the edges,
	 * This would cause dead loop until Java Heap out of memory
	 */
	@Test
	public void testMatchRule_LoopExistInInputRule(){
		Solution solution = new Solution();
		List<String> baseRuleList = new ArrayList<String>();
		baseRuleList.add("A>C");
		baseRuleList.add("A>B");
		baseRuleList.add("B>C"); // This edge can't be removed to cause the loop.
		baseRuleList.add("B>A");
		baseRuleList.add("C>D");
		List<String> ruleList = new ArrayList<String>();
		ruleList.add("A,B>D");
		ruleList.add("A>C");
		ruleList.add("D>A");
		List<Boolean> outputList = solution.matchRule(baseRuleList, ruleList);
		Assert.assertTrue(outputList.get(0));
		Assert.assertTrue(outputList.get(1));
		Assert.assertFalse(outputList.get(2));
	}
	
	@Test
	public void testMatchRule_NormalCase2(){
		Solution solution = new Solution();
		List<String> baseRuleList = new ArrayList<String>();
		baseRuleList.add("A,B,C>D");
		baseRuleList.add("B,F>G");
		baseRuleList.add("G>A");
		List<String> ruleList = new ArrayList<String>();
		ruleList.add("G>D");	
		ruleList.add("D>G");	
		List<Boolean> outputList = solution.matchRule(baseRuleList, ruleList);
		Assert.assertTrue(outputList.get(0));
		Assert.assertFalse(outputList.get(1));
	}
}
