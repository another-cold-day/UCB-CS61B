package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern="";

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        if(wordLength<1)
        {
            throw new IllegalArgumentException();
        }
        List<String> dictionary=FileUtils.readWordsOfLength(dictionaryFile,wordLength);
        if(dictionary.isEmpty())
        {
            throw new IllegalStateException();
        }
        int numWords=dictionary.size();
        int randomlyChosenWordNumber=StdRandom.uniform(numWords);
        chosenWord = dictionary.get(randomlyChosenWordNumber);
        for(int i=0;i<wordLength;i++)
        {
            this.pattern+='-';
        }
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        String newpattern="";
        int count=0;
        for(int i=0;i<this.pattern.length();i++)
        {
            if(letter==this.chosenWord.charAt(i))
            {
                count++;
                newpattern+=letter;
            }
            else
            {
                newpattern+=this.pattern.charAt(i);
            }
        }
        this.pattern=newpattern;
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
        return this.chosenWord;
    }
}
