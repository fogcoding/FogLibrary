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

// 思路

{
    //遍历每个元素
    for(int n=0;n<array.length;n++){
        //取出对应个数的元素
        pullElement();
    }
}

public List pullElement(int number,List document,List result){
    // 但临时数组里的元素个数等于n/遍历目录的数位>目录的个数同时满足时跳出循环，否则移植执行
    for(int j = 0 ;list.length == n && j > document.length ;){
        //如果临时数组里面不包含此元素则添加
        if(!list.contains(document.get(j++))){
            list.add(document.get(j));
            //但临时数组个数已经满足要求就添加进最终set
            if(list.length==n){
                result.add(list);
                //移除最后一个元素，添加下一个值形成遍历操作
                list.removeLast();
                //只移除最后一个元素，漏掉了一些情况eg:{1,2,3,4}    取三个->{1,2,3},{1,2,4},{1,3,4},{2,3,4}
                //这种方法{1，3，4}这个情况就会被漏掉
                //解决设想：弄出一个数组array[0][0][0]来代表取值操作，通过操作数组来实现所有的组合
            }
        }
        //当取到最后一个元素且目标元素组合的个数大于2，使得开始遍历的位置右移一格
        if(j==document.length-1 && n>2){
            int count = 1;
            j=count++;
        }
    }
}

public void flagArray(int n){
    List list = new List(n);        //这里需要每个元素赋值为0
    while(list.get(0) != document.length-n-1){
        int i=list.length;
        int temp = list.get(i)++;
        list.add(i;temp);
        if(list.get(i) < document.size()){
            //按照数组信息读取对应的组合
            parsingArray();
        }else{
            int j = i;
            while(list.get(j--) >= document.size()){
              list.add(j,0);
              int temp1 = list.get(j)++;
              list.add(j,temp1);
            }
        }
    }
}

public list parsingArray(List list){
    for(int i=0;i<list.size();i++){
        tempList.add(document.get(list.get(i));
    }
}
