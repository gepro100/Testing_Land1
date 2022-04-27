//package Testing_Land1;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Geldmanager{
    private static HashMap <Integer, String> geschaeft;
    private static HashMap <Integer, String> kategorie;
    private static HashMap <Integer, String> betrag;

    private static void add(String g, String k, String b){
        int key = (int) geschaeft.size();
        geschaeft.put(key,g);
        kategorie.put(key,k );
        betrag.put(key,b);
        return;
    }

    private static void report_shop(){
        Map<Integer, String> sortedMap = 
        geschaeft.entrySet().stream()
        .sorted(Entry.comparingByValue())
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sortedMap);
        return;
    }
    private static void report_category(){
        Map<Integer, String> sortedMap = 
     kategorie.entrySet().stream()
    .sorted(Entry.comparingByValue())
    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                              (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sortedMap);
        return;
    }

    public static void main(String[] args){
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        while(!exit){
            System.out.println("comands are:\nexit\nadd <geschäft> <kategorie> <betrag>\n"+
            "report shop\n"+"report category");
            String line = in.nextLine();
            System.out.println(line);
            String[] userChoice = line.split(" ");
            if(userChoice[0].equals("add")){
                String tmp_geschaeft = userChoice[1];
                String tmp_kategorie = userChoice[2];
                String tmp_betrag = userChoice[3];
                add(tmp_geschaeft,tmp_kategorie,tmp_betrag);
            }else if (userChoice[0].equals("exit")){
                System.out.println("OK Bye!");
                exit = true;
            }else if(userChoice[0].equals("report")){
                String to_report = userChoice[1];
                if(to_report.equals("shop")){
                    report_shop();
                }else if(to_report.equals("category")){
                    report_category();
                }
            }else{
                System.out.println("You wrote:\n"+ userChoice+"\nplease try again...");
            }
        }
        return;
    }
}
