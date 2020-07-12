
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.*;
import java.io.*;
import java.util.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies()
    {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique()
    {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String s : fr.words())
        {
            s = s.toLowerCase();
            int idx = myWords.indexOf(s);
            if(idx == -1)
            {
                myWords.add(s);
                myFreqs.add(1);
            }else{
                int val = myFreqs.get(idx);
                myFreqs.set(idx, val+1);
            }
        }
    }
    
    public int findIndexOfMax()
    {
        int max=0;
        for(int k=0; k<myFreqs.size(); k++)
        {
            if(myFreqs.get(k) > myFreqs.get(max))
            {
                max = k;
            }
        }
        return max;
    }
    
    public void testfindUnique()
    {
        findUnique();
        System.out.println("Total no of unique words are : " + myFreqs.size());
        for(int i=0; i<myWords.size(); i++)
        {
            System.out.println(myWords.get(i) + " " + myFreqs.get(i));
        }
        int max = findIndexOfMax();
        System.out.println("word with highest number of occurance is " + myWords.get(max) + " " + myFreqs.get(max));
        System.out.println("Total no of unique words are : " + myFreqs.size());
    }
}
