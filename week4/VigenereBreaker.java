import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder str = new StringBuilder(message);
        StringBuilder slicestr = new StringBuilder();
        for(int k=whichSlice; k< message.length(); k = k + totalSlices)
        {
            char c = str.charAt(k);
            if(Character.isLetter(c))
            {
                slicestr.append(c);
            }
        }
        return slicestr.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker ck = new CaesarCracker(mostCommon);
        for(int k=0 ; k < klength ; k++)
        {
            String slice = sliceString(encrypted, k , klength);
            int pass = ck.getKey(slice);
            key[k] = pass;
        }
        return key;
    }
    
    public HashSet readDictionary(FileResource fr)
    {
        HashSet<String> lines = new HashSet<String>();
        for(String line : fr.lines())
        {
            String lowline = line.toLowerCase();
            lines.add(lowline);
        }
        return lines;
    }
    
    public int countWords(String message, HashSet<String> dictionary)
    {
        int count = 0;
        String[] words = message.split("\\W+");
        for(String word : words)
        {
            if(dictionary.contains(word))
            {
                count += 1;
            }
        }
        return count;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        int max = 0;
        char[] alpha = new char[1];
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(String words : dictionary)
        {
            for(int k=0; k<words.length() ; k++)
            {
                char c = words.charAt(k);
                if(map.containsKey(c))
                {
                    map.put(c, map.get(c) + 1);
                }else{
                    map.put(c,1);
                }
            }
        }
        for(char c : map.keySet())
        {
            if(map.get(c) > max)
            {
                max = map.get(c);
                alpha[0]= c;
            }
        }
        return alpha[0];
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        char mostCommon = mostCommonCharIn(dictionary);
        int max = 0;
        int valid = 0;
        int[] k = {0};
        ArrayList<String> str = new ArrayList<String>();
        for(int i=1; i<=100; i++)
        {
            int[] key = tryKeyLength(encrypted,i,mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String s = vc.decrypt(encrypted);
            int count = countWords(s,dictionary);
            valid = valid+ count;
            if(count > max)
            {
                max = count;
                if(str.size() == 0)
                {
                    str.add(s);
                    k[0] = i;
                }
                else
                {
                    str.clear();
                    str.add(s);
                    k[0]= i;
                }
            }
        }
        System.out.println("The key is " + k[0] + " long in length");
        return str.get(0);
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>>languages)
    {
        for(String s : languages.keySet())
        {
            String ans = breakForLanguage(encrypted, languages.get(s));
            System.out.println("language used : " + s);
            System.out.println(ans.substring(0, 100));
        }
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        int klength = 4;
        char mostCommon = 'e';
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] key = tryKeyLength(encrypted, klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(key);
        String s = vc.decrypt(encrypted);
        System.out.println(vc.decrypt(encrypted));
    }
    
    public void breakVigenere2()
    {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource f = new FileResource();
        HashSet<String> dictionary = readDictionary(f);
        String s = breakForLanguage(encrypted, dictionary);
        System.out.println(countWords(s.toLowerCase(), dictionary));
        System.out.println(s.substring(0, 100));
    }
    
    public void breakVigenere3()
    {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            if(!map.containsKey(f.getName()))
            {
                FileResource file = new FileResource(f);
                HashSet<String> hash = readDictionary(file);
                map.put(f.getName(), hash);
                System.out.println("reading....");
                //System.out.println(map.get(f.getName()));
            }
        }
        
        breakForAllLangs(encrypted, map);
    }
}
