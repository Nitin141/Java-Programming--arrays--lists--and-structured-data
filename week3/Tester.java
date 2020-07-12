
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        log.printAll();
    }
    
    public void testUniqueIp()
    {
        LogAnalyzer ip = new LogAnalyzer();
        ip.readFile("weblog2_log");
        int num = ip.countUniqueIPs();
        System.out.println(num);
    }
    
    public void testprintAllHigherThanNum()
    {
        LogAnalyzer higher = new LogAnalyzer();
        higher.readFile("weblog1_log");
        higher.printAllHigherThanNum(400);
    }
    
    public void testuniqueIPVisitsOnDay()
    {
        LogAnalyzer day = new LogAnalyzer();
        day.readFile("weblog2_log");
        String d = "Sep 27";
        ArrayList<String> date = day.uniqueIPVisitsOnDay(d);
        System.out.println(date);
    }
    
    public void testcountUniqueIPsInRange()
    {
        LogAnalyzer range = new LogAnalyzer();
        range.readFile("weblog2_log");
        System.out.println(range.countUniqueIPsInRange(400,499));
    }
    
    public void testcountVisitsPerIP()
    {
        LogAnalyzer hash = new LogAnalyzer();
        hash.readFile("weblog2_log");
        HashMap<String, Integer> map = hash.countVisitsPerIP();
        System.out.println("The hash map is " + map);
        System.out.println("max number visited by any ip is " + hash.mostNumberVisitsByIP(map));
        System.out.println("ip that visited max time is/are " + hash. iPsMostVisits(map));
    }
    
    public void testIpOndDays()
    {
        LogAnalyzer Ips = new LogAnalyzer();
        Ips.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> newmap = Ips.iPsForDays();
        System.out.println("The Hashmap is " + newmap);
        System.out.println("day with most ip address visit is " + Ips.dayWithMostIPVisits(newmap));
        String day = "Sep 30";
        System.out.println("ip address that had th most accesses on " + day + " " + Ips.IpsWithMostVisitsOnDay(newmap,day));
    }
}
