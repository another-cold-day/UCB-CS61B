package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }
    //this method helps me find the possible words
    ArrayList<String> getPossible(String pattern,List<Character> guesses)
    {
        ArrayList<String> possible=new ArrayList<>();
        for(String s:words)
        {
            if(s.length()!=pattern.length()) continue;
            for(int i=0;i<pattern.length();i++)
            {
                if(pattern.charAt(i)!='-'&&pattern.charAt(i)!=s.charAt(i)) break;
                if(pattern.charAt(i)=='-'&&guesses.contains(s.charAt(i))) break;
                if(i==pattern.length()-1) possible.add(s);
            }
        }
        return possible;
    }
    //this method helps me get the freqMap
   /* HashMap<Character,Integer> getFreqMap(ArrayList<String> possible, List<Character> guesses)
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
    }*/
    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        ArrayList<String> possible = getPossible(pattern,guesses);
        HashMap<Character,Integer> m=FileUtils.getFreqMap(possible,guesses);
        char result=0;
        int count=0;
        for(Character c:m.keySet())
        {
            if(m.get(c)>count)
            {
                count=m.get(c);
                result=c;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
