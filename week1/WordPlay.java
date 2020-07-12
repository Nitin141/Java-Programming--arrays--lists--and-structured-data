
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.*;
import java.io.*;
public class WordPlay {
    public boolean isVowel(char ch)
    {
        String vowel = "AEIOUaeiou";
        for(int i=0 ; i<vowel.length(); i++)
        {
            if(vowel.charAt(i) == ch)
            {
                return true;
            }
        }
        return false;
    }
    
    public String replaceVowels(String phrase, char ch)
    {
        String vowel = "AEIOUaeiou";
        StringBuilder newPhrase = new StringBuilder(phrase);
        for(int i=0; i<phrase.length() ;i++)
        {
            for(int j=0 ; j< vowel.length() ; j++)
            {
                if(isVowel(newPhrase.charAt(i)))
                {
                    newPhrase.setCharAt(i,ch);
                }
            }
        }
        return newPhrase.toString();
    }
    
    public String emphasize(String phrase, char ch)
    {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for(int i=0; i<phrase.length() ;i++)
        {
            if(newPhrase.charAt(i) == Character.toLowerCase(ch) || newPhrase.charAt(i) == Character.toUpperCase(ch))
            {
                if(i % 2 == 0 || i == 0)
                {
                    newPhrase.setCharAt(i,'*');
                }else{
                    newPhrase.setCharAt(i,'+');
                }
            }
        }
        return newPhrase.toString();
    }
    
    public void testWordPlay()
    {
        char ch = 'a';
        System.out.println(isVowel(ch));
    }
    
    public void testreplaceVowels()
    {
        String phrase = "Hello World";
        char ch = '*';
        System.out.println(replaceVowels(phrase, ch));
    }
    
    public void testemphasize()
    {
        String phrase = "Mary Bella Abracadabra";
        char ch = 'a';
        System.out.println(emphasize(phrase, ch));
    }
}
