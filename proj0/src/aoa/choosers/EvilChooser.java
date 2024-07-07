package aoa.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern="";
    private List<String> wordPool=new ArrayList<>();

    public EvilChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in this constructor.
        if (wordLength < 1) {
            throw new IllegalArgumentException();
        }
        wordPool = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if (wordPool.isEmpty()) {
            throw new IllegalStateException();
        }
        for(int i=0;i<wordLength;i++)
        {
            pattern+='-';
        }

    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        int count=0;
        Map<String,Integer> patternpool= new TreeMap<>();
        Map<String,List<String>> patternpool2=new TreeMap<>();
        for(int i=0;i< wordPool.size();i++)
        {
            String nowpattern="";
            for(int j=0;j<this.pattern.length();j++)
            {
                if(wordPool.get(i).charAt(j)==letter)
                {
                    nowpattern+=letter;
                }
                else
                {
                    nowpattern+=this.pattern.charAt(j);
                }
            }
           // System.out.println(nowpattern);
            if(!patternpool.containsKey(nowpattern))
            {
                patternpool.put(nowpattern,1);
                List<String> l=new ArrayList<>();
                l.add(wordPool.get(i));
                patternpool2.put(nowpattern,l);
            }
            else
            {
                patternpool.put(nowpattern,patternpool.get(nowpattern)+1);
                List<String> l1=patternpool2.get(nowpattern);
                l1.add(wordPool.get(i));
                patternpool2.put(nowpattern,l1);
            }
        }
       // System.out.println(patternpool);
       // System.out.println(this.wordPool);
        String finalpattern="";
        for(String key:patternpool.keySet())
        {
            //System.out.print(key+' ');
            //System.out.println(patternpool.get(key));
            if(count<patternpool.get(key))
            {
                count=patternpool.get(key);
                finalpattern=key;
               // System.out.print(key+' ');
               // System.out.println(patternpool.get(key));
            }
        }
        count=0;
        for(int i=0;i<this.pattern.length();i++)
        {
            if(this.pattern.charAt(i)!=finalpattern.charAt(i))
            {
                count++;
            }
        }
        this.pattern=finalpattern;
        //List<String> newwordpoll=new ArrayList<>();
        /*for(int i=0;i<wordPool.size();i++)
        {
            String s=wordPool.get(i);
            for(int j=0;j<this.pattern.length();j++)
            {
                System.out.println(s.charAt(j));
                if(this.pattern.charAt(j)!='-'&&this.pattern.charAt(j)!=s.charAt(j))
                {
                    //System.out.println("do");
                    break;
                }
                if(j==this.pattern.length()-1)
                {
                    newwordpoll.add(s);
                    //System.out.println(s);
                }
            }
        }*/
       // System.out.println(newwordpoll);
        /*for(int i=0;i<this.wordPool.size();i++)
        {
            Syst*/
        this.wordPool.clear();
        this.wordPool=patternpool2.get(this.pattern);
        return count;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return this.pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return this.wordPool.get(0);
    }
}
