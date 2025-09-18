package com.e_commerce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solutions {


	public boolean isAnagram(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return false;
		}
		char array1[] = str1.toCharArray();
		char array2[] = str2.toCharArray();
		Arrays.sort(array1);
		Arrays.sort(array2);
		return Arrays.equals(array1, array2);
	}

	public void delete(int position, String words[]) {
		int j = position;
		int i = position + 1;
		while (words.length > i) {
			words[j] = words[i];
			++i;
			++j;
		}
		words[words.length - 1] = null;

	}

	public List<String> removeAnagrams(String[] words) {
		int j = 1;
		int i = j - 1;
		while (j < words.length) {
			if (isAnagram(words[i], words[j])) {

				delete(j, words);
			} else {
				++i;
				++j;
			}

		}

		List<String> list = new ArrayList<String>();
		for (String res : words) {
			if (res != null) {
				list.add(res);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int nums[] = { 1, 2 };
		Solutions solutions=new Solutions();
		String words[]= {"abba","baba","bbaa","cd","cd"};
	List<String>list=	solutions.removeAnagrams(words);
	for(String str:list) {
		System.out.println(str);
	}
		// singleNumber(nums);

	}

}
