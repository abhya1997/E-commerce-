package com.e_commerce;

public class Solution1 {//3,2,4   6  //2,3,4
    public static int[] twoSum(int[] nums, int target) {
      //  Arrays.sort(nums);
        int answer[] = new int[2];
        
        int sum = 0;
       //[0,3,-3,4,-1] (-1)
      for(int i=0;i<nums.length;++i){
       for(int j=i+1;j<nums.length;++j){
            sum = sum + nums[i] + nums[j];
            if(sum==target){
                answer[0]=i;
                answer[1]=j;
                break;
            }
            else{
                sum=0;
            }
            

       }

      }
      for(int num:answer) {
    	  System.out.println(num);
      }

        return answer;

    }
    public static void main(String[] args) {
		int nums[]= {0,3,-3,4,-1};
		int target =-1;
		System.out.println(twoSum(nums, target));
		//System.out.println(0-1);
		//System.out.println(-1==-1);
	}
}