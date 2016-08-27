// Given a collection of integers that might contain duplicates, nums, return all possible subsets. 

// Note: The solution set must not contain duplicate subsets. 

// For example,
//  If nums = [1,2,2], a solution is: 
// [
//   [2],
//   [1],
//   [1,2,2],
//   [2,2],
//   [1,2],
//   []
// ]


// [REF:](https://leetcode.com/problems/subsets-ii/)

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Interger>> result = new List<List<Interger>>;
        
        
        
    }
    
    public void recursion(List result,int[] nums){
        List<Integer> temp = new List<Integer>;
        //choosen one element
        for(int i=0;i<nums.length;i++){
            if(!temp.contains(num[i])){
               temp.add(nums[i]); 
            }
        }
        // choosen count
        for(int j=0;j<count;j++){
            recursion(result,nums);
        }
        
        result.add(temp);
        temp = null;
    }
    
}
