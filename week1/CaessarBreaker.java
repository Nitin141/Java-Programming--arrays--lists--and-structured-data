
/**
 * Write a description of CaessarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.*;
import java.io.*;
public class CaessarBreaker {
    public int[] countLetters(String message)
    {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k<message.length(); k++)
        {
            char ch = Character.toLowerCase(message.charAt(k));
            int idx = alph.indexOf(ch);
            if(idx != -1)
            {
                counts[idx] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values)
    {
        int max = 0;
        for(int k=0; k<values.length; k++)
        {
            if(values[k] > values[max])
            {
                max = k;
            }
        }
        return max;
    }
    
    public String decrypt(String encrypted)
    {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex -4;
        if(maxDex < 4)
        {
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }
    
    public String halfOfString(String message, int start)
    {
        String answer = "";   	
        for(int k=start; k<message.length(); k+=2)
        {
            answer = answer + message.charAt(k); 
        }
        return answer;
    }
    
    public int getKey(String s)
    {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex -4;
        if(maxDex < 4)
        {
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }
    
    public void decryptTwoKeys(String encrypted)
    {
        CaesarCipher cc = new CaesarCipher();
        String str1 = halfOfString(encrypted , 0);
        String str2 = halfOfString(encrypted , 1);
        System.out.println("the Str1 is " + str1 + " and str2 is " + str2);
        int key1 = getKey(str1);
        int key2 = getKey(str2);
        System.out.println("the keys are: " + key1 + " " + key2);
        System.out.println(cc.encryptTwoKeys(encrypted, 26-key1, 26-key2));
    }
    
    public void testdecrypt()
    {
        String encrypted = "Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth";
        System.out.println(decrypt(encrypted));
    }
    
    public void testdecryptTwokeys()
    {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println(encrypted);
        decryptTwoKeys(encrypted);
    }
}
