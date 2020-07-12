
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.*;
import java.io.*;
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts)
    {
        for(String word : resource.words())
        {
            int count = 0;
            int k = word.length();
            if(k < counts.length)
            {
                for(int j = 0; j < k; j++)
                {
                    if(Character.isLetter(word.charAt(j)) || (j < k-1))
                    {
                        count = count + 1;
                    }
                }
                String realword = word.substring(0,count);
                int m = realword.length();
                counts[m] = counts[m] + 1 ;
            }
            else
            {
                counts[counts.length] = counts[counts.length] + 1 ; 
            }
        }
    }
    
    public int indexOfMax(int[] values)
    {
        int max = 0;
        for(int k=0; k<values.length; k++)
        {
            if(max < values[k])
            {
                max = k;
            }
        }
        return max;
    }
    
    public void testCountWordLengths()
    {
        int[] counts = new int[31];
        FileResource fr = new FileResource();
        countWordLengths(fr, counts);
        for(int i=1; i<counts.length ; i++)
        {
            System.out.println(counts[i] + " words of length " + i);
        }
        int idx = indexOfMax(counts);
        System.out.println("max words are of length " + idx);
    }
}
