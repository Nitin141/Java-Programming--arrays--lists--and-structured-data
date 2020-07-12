
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class tester {
    public void testCaesarCipher() 
    {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cc = new CaesarCipher(4);
        String enc = cc.encrypt(input);
        System.out.println(enc);
        String dec = cc.decrypt(enc);
        System.out.println(dec);
    }
    
    public void testCaesarCracker()
    {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCracker ck = new CaesarCracker();
        System.out.println(ck.decrypt(input));
    }
    
    public void testVigenereCipher()
    {
        int[] key = {17, 14, 12, 4};
        FileResource fr = new FileResource();
        String input = fr.asString();
        VigenereCipher vc = new VigenereCipher(key);
        String enc = vc.encrypt(input);
        System.out.println(enc);
        String dec = vc.decrypt(enc);
        System.out.println(dec);
    }
    
    public void testsliceString()
    {
        VigenereBreaker vb = new VigenereBreaker();
        String res = vb.sliceString("abcdefghijklm", 2, 3);
        System.out.println(res);
    }
    
    public void testtryKeyLength()
    {
        FileResource fr = new FileResource();
        String input = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int key[] = vb.tryKeyLength(input, 4, 'e');
        for(int i=0; i< 4; i++)
        {
            System.out.println(key[i]);
        }
    }
    
    public void testVigenereBreaker()
    {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
    
    void test()
    {
        FileResource fr = new FileResource();
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.readDictionary(fr));
    }
    
    void testbreakVigenere2()
    {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere2();
    }
    
    void testbreakVigenere3()
    {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere3();
    }
}
