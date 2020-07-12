
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.*;
import java.io.*;
import java.util.*;
public class WordsInFiles {
    private HashMap<String, ArrayList<String> > map;
    public WordsInFiles()
    {
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f)
    {
        String fname = f.getName();
        FileResource fr = new FileResource(f);
        for(String word : fr.words())
        {
            if(map.containsKey(word) && !map.get(word).contains(fname))
            {
                ArrayList<String> oldlist = map.get(word);
                oldlist.add(fname);
                map.put(word, oldlist);
            }
            else if(!map.containsKey(word))
            {
                ArrayList<String> newlist = new ArrayList<String>();
                newlist.add(fname);
                map.put(word,newlist);
            }
        }
    }
    
    public void buildWordFromFileMap()
    {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber()
    {
        int max = 0;
        for(ArrayList<String> v : map.values())
        {
            if(v.size() > max)
            {
                max = v.size();
            }
        }
        return max;
    }
    
    public ArrayList  wordsInNumFiles(int number)
    {
        ArrayList<String> words = new ArrayList<String>();
        for(String s : map.keySet())
        {
            if(map.get(s).size() == number)
            {
                words.add(s);
            }
        }
        return words;
    }
    
    public void printFilesIn(String word)
    {
        for(String s : map.keySet())
        {
            if(s == word)
            {
                for(int k = 0; k< map.get(s).size(); k++)
                {
                    System.out.println(map.get(s).get(k));
                }
            }
        }
    }
    
    public void tester()
    {
        int count =0;
        buildWordFromFileMap();
        /*for(String s : map.keySet())
        {
            System.out.println(s + "\t");
            for(int k = 0; k< map.get(s).size(); k++)
                {
                    System.out.println(map.get(s).get(k));
                }
        }*/
        int max = maxNumber();
        System.out.println("max number of files any word is in : "+ max);
        for(String s : map.keySet())
        {
            if(map.get(s).size() == 4)
            {
                System.out.println("the word " + s + " is in following file : ");
                printFilesIn(s);
                count= count+1;
            }
        }
        System.out.println(map.get("laid"));
        System.out.println(map.get("tree"));
        System.out.println(map.size());
        System.out.println(count);
    }
}
