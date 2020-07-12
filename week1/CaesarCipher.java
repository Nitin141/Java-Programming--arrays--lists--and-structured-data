
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.*;
import java.io.*;
public class CaesarCipher {
    public String encrypt(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = alphabet.substring(key,26) + alphabet.substring(0,key) +alphabet.substring(26+key) + alphabet.substring(26,26+key);
        //System.out.println(shiftedAlphabet);
        for(int i=0 ; i<encrypted.length() ; i++)
        {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1)
            {
                encrypted.setCharAt(i, shiftedAlphabet.charAt(idx));
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2)
    {
        String withkey1 = encrypt(input, key1);
        String withkey2 = encrypt(input, key2);
        //StringBuilder encrypted = new StringBuilder(withkey1);
        StringBuilder sb = new StringBuilder(withkey2);
        for(int i = 0; i< sb.length() ; i += 2)
        {
            sb.setCharAt(i,withkey1.charAt(i));
        }
        return sb.toString();
    }    
    
    public void testencrypted()
    {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println("key is " + (26-key) + "\n" + decrypted);
    }
    
    public void testencryptTwoKeys()
    {
        int key1 = 14;
        int key2 = 24;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message,key1,key2);
        System.out.println("keys are " + key1 + " " + key2 + "\n" + encrypted);
        System.out.println(encryptTwoKeys(message,26-key1,26-key2));
    }
}
