
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class GladLibMap {
    public int count = 0;
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWord = new ArrayList<String>();
    private ArrayList<String> lab = new ArrayList<String>();
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", "adjective", "name", "color", "timeframe", "verb", "fruit"};
        for(String s : labels)
        {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(!lab.contains(label))
        {
            lab.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        while(true)
        {
            String sub = getSubstitute(w.substring(first+1,last));
            if(usedWord.contains(sub))
            {
                continue;
            }
            else{
                usedWord.add(sub);
                count += 1;
                return prefix+sub+suffix;
            }
        }
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int  totalWordsInMap()
    {
        int count1 = 0;
        for(String s : myMap.keySet())
        {
            count1 = count1 + myMap.get(s).size();
        }
        return count1;
    }
    
    public int totalWordsConsidered()
    {
        int count2 = 0;
        for(String s : myMap.keySet())
        {
            if(lab.contains(s))
            {
                count2 = count2 + myMap.get(s).size();
            }
        }
        return count2;
    }
    
    public void makeStory(){
        usedWord.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("words replaced : " + count);
        System.out.println("Total no of words : " + totalWordsInMap());
        System.out.println("Total no of words consider : " + totalWordsConsidered());
    }
    
}
