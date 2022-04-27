//package de.propra.ausgaben;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Eintrag{
  String geschaeft;
  String kategorie;
  double betrag;
  public Eintrag(String geschaeft, String kategorie, double betrag) {
    this.geschaeft = geschaeft;
    this.kategorie = kategorie;
    this.betrag = betrag;
  }
  public String getGeschaeft() {
    return geschaeft;
  }
  public String getKategorie() {
    return kategorie;
  }
  public double getBetrag() {
    return betrag;
  }
}

public class Ausgabenbuch {
  ArrayList <Eintrag> eintraege = new ArrayList<Eintrag>();
  private final Scanner stdin = new Scanner(System.in);

  // FIXME: Diese Methode implementieren
  private void report(String[] parts) {
    // Bei Fehlern invalid() aufrufen und die Methode mit return beenden
    // Prüfen: parts hat 2 Elemente
    if(parts.length!=2){
      invalid();
      return;
    }
    HashMap <String, Double> kat_map = new HashMap<String, Double>();
    // 0 ist "report", 1 ist entweder "category" oder "shop"
    switch(parts[1]){
      case "category":
        for(Eintrag i : eintraege){
          //falls key vorhanden, addiere preis drauf, sonst ist preis erster eintrag zu key
          try{
            kat_map.put(i.kategorie, kat_map.get(i.kategorie) + i.betrag);
          }catch(NullPointerException e){
            kat_map.put(i.kategorie, i.betrag);
          }
        }
        break;
      case "shop":
          //falls key vorhanden, addiere preis drauf, sonst ist preis erster eintrag zu key
        for(Eintrag i : eintraege){
          if(kat_map.containsKey(i.geschaeft)){
            kat_map.put(i.geschaeft, kat_map.get(i.geschaeft) + i.betrag);
          }else{
            kat_map.put(i.geschaeft, i.betrag);
          }
        }
        break;
      default:
        invalid();
        return;
    }
    System.out.println(kat_map);
  }

  // FIXME: Diese Methode implementieren
  private void add(String[] parts) {
    // Bei Fehlern invalid() aufrufen und die Methode mit return beenden
    // Prüfen: parts hat 4 Elemente
    if(parts.length!=4){
      invalid();
      return;
    }
    // 0 ist "add", 1 ist der Name des Geschäfts, 2 ist der Name der Kategorie, 3 ist ein String, in dem ein Double Wert steht
    Eintrag e = new Eintrag(parts[1], parts[2], Double.parseDouble(parts[3]));
    eintraege.add(e);
    return;
  }

  // FIXME: Diese Methode implementieren
  public void run() {
    boolean repeat = true;
    // Hier muss in geeigneter Weise readAndProcess aufgerufen werden
    while(repeat){
      repeat = readAndProcess();
    }
    return;
  }


  /**
   * Diese Methode soll aufgerufen werden, wenn es einen Fehler bei den Parametern gibt
   */
  private void invalid() {
    System.out.println("Ungültige Eingabe.");
  }


  /**
   * Liest eine Zeile von der Eingabeaufforderung
   * Ruft je nach erstem Wort die passende Methode auf
   * @return Die Methode gibt true zurück, wenn das Programm
   * nach der Verarbeitung des Kommandos weiterlaufen soll
   */
  private boolean readAndProcess() {
    String line;
    line = stdin.nextLine();
    String[] parts = line.split(" ");
    switch (parts[0]) {
      case "add": {
        add(parts);
        return true;
      }
      case "report": {
        report(parts);
        return true;
      }
      case "exit": {
        return false;
      }
      default: {
        invalid();
        return true;
      }
    }
  }

  public static void main(String[] args) {
    Ausgabenbuch ausgabenbuch = new Ausgabenbuch();
    ausgabenbuch.run();
  }

}
