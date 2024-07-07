package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }
    public ArrayList<String> getPossible(String pattern,List<Character> guesses)
    {
        ArrayList<String> l=new ArrayList<>();
        for(String s:words)
        {
            if(s.length()==pattern.length())
            {
                for(int i=0;i<pattern.length();i++)
                {
                    if(pattern.charAt(i)!='-')
                    {
                        if(s.charAt(i)!=pattern.charAt(i))
                            break;
                    }
                    if(i==pattern.length()-1)
                        l.add(s);
                }
            }
        }
        return l;
    }
    /*HashMap<Character,Integer> getFreqMap(ArrayList<String> possible,List<Character> guesses)
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
     *  PATTERN. */
    //this method helps me to get the possible words in the list
   /* public ArrayList<String> getPossible(String pattern,List<Character> guesses)
    {
        ArrayList<String> l=new ArrayList<>();

        return l;
    }*/
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
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
//        for(String s: palfg.getPossible("e--e",List.of('e')))
//        {
//            System.out.println(s);
//        }
    }
}