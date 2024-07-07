package aoa.utils;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileUtils {
    public static List<String> readWords(String filename) {
        List<String> returnList = new ArrayList<>();
        In in = new In(filename);
        while (!in.isEmpty()) {
            returnList.add(in.readString());
        }
        return returnList;
    }

    public static List<String> readWordsOfLength(String filename, int length) {
        List<String> returnList = new ArrayList<>();
        In in = new In(filename);
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() == length) {
                returnList.add(word);
            }
        }
        return returnList;
    }
    //this method helps me get the freqMap
    public static HashMap<Character,Integer> getFreqMap(ArrayList<String> possible, List<Character> guesses)
    {
        HashMap<Character,Integer> m=new HashMap<>();
        for(Character c='a';c<='z';c++)
        {
            if(!guesses.contains(c))
            {
                Integer count=0;
                for(String s:possible)
                {
                    for(int i=0;i<s.length();i++)
                    {
                        if(c==s.charAt(i))
                            count++;
                    }
                }
                m.put(c,count);
            }
        }
        return m;
    }
}
