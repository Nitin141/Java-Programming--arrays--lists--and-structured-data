
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.*;
import java.io.*;
import java.util.*;
public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> charCount;
    
    public CharactersInPlay()
    {
        charNames = new ArrayList<String>();
        charCount = new ArrayList<Integer>();
    }
    
    public void update(String person)
    {
        int idx = charNames.indexOf(person);
        if(idx == -1)
        {
            charNames.add(person);
            charCount.add(1);
        }
        else{
            int val = charCount.get(idx);
            charCount.set(idx, val + 1);
        }
    }
    
    public void findAllCharacters()
    {
        charNames.clear();
        charCount.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines())
        {
            //line = line.toLowerCase();
            int period = line.indexOf(".");
            if(period != -1)
            {
                String person = line.substring(0, period);
                update(person);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2)
    {
        for(int i=0; i<charNames.size(); i++)
        {
            if(charCount.get(i) >= num1 && charCount.get(i) <= num2)
            {
                System.out.println(charNames.get(i) + " " + charCount.get(i));
            }
        }
    }
    
    public void tester()
    {
        findAllCharacters();
        for(int k=0; k< charNames.size(); k++)
        {
            if(charCount.get(k) >= 0)
            {
                System.out.println(charNames.get(k) + " " + charCount.get(k));
            }
        }
        System.out.println("=========================");
        System.out.println("=========================");
        int num1 = 10;
        int num2 = 15;
        charactersWithNumParts(num1, num2);
    }
}
