
/**
 * Write a description of Codonandother here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.*;
import java.io.*;
import java.util.*;
public class Codonandother {
    private HashMap<String, Integer> map;
    public Codonandother()
    {
        map = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna)
    {
        map.clear();
        for(int k=start; k <dna.length(); k+=3)
        {
            if(k+3 > dna.length())
            {
                break;
            }
            String codon = dna.substring(k, k+3);
            if(map.containsKey(codon))
            {
                map.put(codon, map.get(codon)+1);
            }
            else{
                map.put(codon, 1);
            }
        }
    }
    
    public String getMostCommonCodon()
    {
        int max = 0;
        String[] maxcodon = new String[1];
        for(String s: map.keySet())
        {
            int val = map.get(s);
            if(val > max)
            {
                max = val;
                maxcodon[0] = s;
            }
        }
        return maxcodon[0];
    }
    
    public void printCodonCounts(int start, int end)
    {
        for(String s: map.keySet())
        {
            if(map.get(s)>= start && map.get(s) <= end)
            {
                System.out.println(s + "\t" +map.get(s));
            }
        }
    }
    
    public void tester()
    {
        int start = 0;
        int starts = 1;
        int end = 7;
        FileResource fr = new FileResource();
        String dna = fr.asString();
        String dna1 = dna.trim();
        buildCodonMap(start, dna1);
        System.out.println("Reading fram starting with " + start + " results in " + map.size() + " unique codons");
        String common = getMostCommonCodon();
        System.out.println("and most common codon is " + common + " with count " + map.get(common));
        System.out.println("count of codon between " + starts + " and " + end + " is ");
        printCodonCounts(starts, end);
    }
}
