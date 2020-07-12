
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry> ();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines())
         {
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs()
     {
         ArrayList<String> uniqueIP = new ArrayList<String>();
         for(LogEntry le : records)
         {
             String ipaddr = le.getIpAddress();
             if(!uniqueIP.contains(ipaddr))
             {
                uniqueIP.add(ipaddr);
             }
         }
         return uniqueIP.size();
     }
     
     public void printAllHigherThanNum(int num)
     {
         for(LogEntry le : records)
         {
             int status = le.getStatusCode();
             if(status > num)
             {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList uniqueIPVisitsOnDay(String someday)
     {
         ArrayList<String> uniqueIP = new ArrayList<String>();
         for(LogEntry le : records)
         {
             String ipaddr = le.getIpAddress();
             if(!uniqueIP.contains(ipaddr))
             {
                Date d = le.getAccessTime();
                //System.out.println(d);
                String day = d.toString();
                //System.out.println(day);
                String date = day.substring(4,10);
                //System.out.println(date);
                if(date.contains(someday))
                {
                    uniqueIP.add(ipaddr);
                }
             }
         }
         return uniqueIP;
     }
     
     public int countUniqueIPsInRange(int low, int high)
     {
         ArrayList<String> uniqueIP = new ArrayList<String>();
         for(LogEntry le : records)
         {
             String ipaddr = le.getIpAddress();
             if(!uniqueIP.contains(ipaddr))
             {
                int status = le.getStatusCode();
                if(status >= low && status <= high)
                {
                    uniqueIP.add(ipaddr);
                }
             }
         }
         return uniqueIP.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP()
     {
         HashMap<String, Integer> map = new HashMap<String, Integer>();
         for(LogEntry le : records)
         {
             String ipadd = le.getIpAddress();
             if(!map.containsKey(ipadd))
             {
                 map.put(ipadd, 1);
             }
             else{
                map.put(ipadd, map.get(ipadd) + 1);
             }
         }
         return map;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> map)
     {
         int max = 0;
         for(Integer v : map.values())
         {
             if(v > max)
             {
                 max = v;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map)
     {
         ArrayList<String> list = new ArrayList<String>();
         int max = mostNumberVisitsByIP(map);
         for(String key : map.keySet())
         {
             if(map.get(key) == max)
             {
                 list.add(key);
             }
         }
         return list;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays()
     {
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records)
         {
             String ipadd = le.getIpAddress();
             Date d = le.getAccessTime();
             String day = d.toString();
             String date = day.substring(4,10);
             if(!map.containsKey(date))
             {
                 ArrayList<String> newList = new ArrayList<String>();
                 newList.add(ipadd);
                 map.put(date,newList);
             }else
             {
                 ArrayList<String> oldList = map.get(date);
                 oldList.add(ipadd);
                 map.put(date,oldList);
             }
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> newmap)
     {
         int max = 0;
         String[] day = new String[1];
         for(String key : newmap.keySet())
         {
             if(newmap.get(key).size() > max)
             {
                 max = newmap.get(key).size();
                 day[0] = key;
             }
         }
         return day[0];
     }
     
     public ArrayList<String> IpsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> newmap, String day)
     {
         HashMap<String, Integer> ip = new HashMap<String, Integer>();
         if(newmap.containsKey(day))
         {
             for(String s : newmap.get(day))
             {
                 if(!ip.containsKey(s))
                 {
                     ip.put(s,1);
                 }
                 else
                 {
                     ip.put(s,ip.get(s) +1);
                 }
             }
         }
         return iPsMostVisits(ip);
     }
}
