

package projekat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import projekat.CustomExceptions.*;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class Agencija{
    private static int rezervacija = 1;
    private static ArrayList<Rezervacija> rezervacije = new ArrayList<>();
    private static Provera provera = new Provera();
    private static Scanner scanner = new Scanner(System.in);

    public static void start(){
        provera.dodajVozilo(new Automobil(1, "Toyota", "Corolla", 2022, 50.0, true, 5));
        provera.dodajVozilo(new Automobil(2, "Honda", "Civic", 2023, 55.0, true, 5));
        provera.dodajVozilo(new Automobil(3, "Porsche", "911 Carrera", 2024, 120.0, true, 2));
        provera.dodajVozilo(new Kombi(4, "Volkswagen", "Transporter", 2023, 70.0, true, 1200));
        provera.dodajVozilo(new Kombi(5, "Renault", "Master", 2020, 95.0, true, 1500));
        provera.dodajVozilo(new Kombi(6, "Mercedes", "Sprinter", 2021, 80.0, true, 1000));

        opcije();
    }

    public static void opcije() {
        boolean pokrenutProgram = true;
        while (pokrenutProgram) {
            prikaziOpcije1();
            int opcija = unos();
            switch (opcija) {
                case 1: 
                    prikaziVozila();
                    break;
                case 2: 
                    prikaziOpcije2();
                    int opcija2 = unos();
                    izvrsiOpciju1(opcija2);
                    break;
                case 3: 
                    prikaziOpcije3();
                    int opcija3 = unos();
                    izvrsiOpciju2(opcija3);
                    break;
                case 0:
                    System.out.println("Kraj programa.");
                    pokrenutProgram = false;
                    break;
            }
        }
    } 
    
    public static void prikaziOpcije1() {
        System.out.println("\n1. Prikazi vozila");
        System.out.println("2. Iznajmljivanje");
        //System.out.println("3. Vrati vozilo");
        System.out.println("3. Rezervacije");
        //System.out.println("5. Vidi rezervacije");
        System.out.println("0. Kraj programa\n");
    }
    
    public static void prikaziOpcije2() {
        System.out.println("\n1. Iznajmi vozilo");
        System.out.println("2. Vrati vozilo");
        System.out.println("0. Izlaz\n");
    }
     
    public static void prikaziOpcije3() {
        System.out.println("\n1. Rezervisi vozilo");
        System.out.println("2. Vidi rezervacije");
        System.out.println("0. Izlaz\n");
    }

    public static void izvrsiOpciju1(int opcija){
        switch (opcija) {
            case 1:
                iznajmljivanje();
                break;
            case 2:
                vracanje();
                break;
            case 3:
                start();
                break;
        }
    }
    
    public static void izvrsiOpciju2(int opcija){
        switch (opcija) {
            case 1:
                rezervacija();
                break;
            case 2:
                prikaziRezervacije();
                break;
            case 3:
                start();
                break;
        }
    }
    
    public static void prikaziVozila() {
        System.out.println("Vozila:");
        for (var v : provera.getVozila()) {
            switch (v) {
                case Automobil a -> a.prikaziInfo();
                case Kombi k -> k.prikaziInfo();
                default -> {
                }
            }
                
        }
    }
    
    public static void iznajmljivanje() {
        String ime = unosImena();
        int voziloID = unosID(); 
        
        try {               
            provera.iznajmiVozilo(voziloID, ime);
        } catch (VehicleNotFoundException | VehicleAlreadyRentedException | NumberFormatException e) {               
            System.err.println(e.getMessage()); 
        }
    }

    public static void vracanje() {
        if (!daLiImaIznajmljenihVozila())
            System.err.println("Nema iznajmljenih vozila.");
        else {
            boolean validnoVracanje = false;
            String ime = unosImena();
            int voziloID;

            while(!validnoVracanje){
                voziloID = unosID();
                try {
                    provera.vratiVozilo(voziloID, ime);
                    validnoVracanje = true;
                } catch (VehicleNotFoundException | VehicleNotRentedException e) {              
                    System.err.println(e.getMessage());
                }   
            }    
        }
    }

    public static void rezervacija(){
        String ime = unosImena(); 
        int voziloID = unosID();  
        if (proveraIDRezervacija(voziloID)) {             
            LocalDate pocetakRezervacije = unosDatuma("pocetak");
            LocalDate krajRezervacije = unosDatuma("kraj");
            
            while(!proveriDatum(pocetakRezervacije, krajRezervacije)){
                pocetakRezervacije = unosDatuma("pocetak");
                krajRezervacije = unosDatuma("kraj");
            }
            
            rezervacije.add(new Rezervacija(rezervacija++, voziloID, pocetakRezervacije, krajRezervacije, ime));
            Rezervacija.upisRezervacija(rezervacije);
            System.out.println("Rezervacija uspesno kreirana. \n");
        }
       
    }

    public static void prikaziRezervacije() {
        ArrayList<Rezervacija> rezJson = Rezervacija.citanjeRezervacija();
        if (rezJson.isEmpty())
            System.err.println("Nije uneta nijedna rezervacija.");
        else {
            System.out.println("\nRezervacije:");
            for (Rezervacija rez : rezJson) 
                System.out.println(rez);      
        }
    }
    
    public static String unosImena() {
        scanner.nextLine();
        String ime = "";
        boolean validanUnos = false;

        while (!validanUnos) {
            System.out.print("Unesite ime: ");
            ime = scanner.nextLine();

            if (sadrziBrojeve(ime)) {
                System.err.println("Ime moze sadrzati samo slova.");
            } else if (ime.equals("")) {
                System.err.println("Morate uneti ime.");
            } else {
                validanUnos = true;
            }
        }
        return ime;
    }

    public static boolean sadrziBrojeve(String text) {
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    
    public static int unos() {
        int opcija = 0;
        boolean validanUnos = false;

        while (!validanUnos) {
            try {
                System.out.print("Izaberite opciju: ");
                opcija = scanner.nextInt();
                validanUnos = true; 
            } catch (InputMismatchException e) {
                System.err.println("Morate uneti broj.");
                scanner.nextLine();
            }
        }
        return opcija;
    }   
    
    public static int unosID() {
        int id = 0;
        boolean validanUnos = false;

        while (!validanUnos) {
            try {
                System.out.print("Unesite ID vozila: ");
                id = scanner.nextInt();
                validanUnos = true; 
            } catch (InputMismatchException e) {
                System.err.println("Morate uneti broj.");
                scanner.nextLine();
            }
        }
        return id;
    }   
    
    public static LocalDate unosDatuma(String tip) {
        LocalDate datum = null;
        boolean validanFormat = false;

        while (!validanFormat) {
            try {
                System.out.print("Unesite " + tip + " rezervacije (GGGG-MM-DD): ");
                String datumString = scanner.next();
                datum = LocalDate.parse(datumString);
                validanFormat = true;
            } catch (DateTimeParseException dtpe) {
                System.err.println("Neispravan format datuma.");
            }
        }
        return datum;
    }
    
    public static boolean proveriDatum(LocalDate pocetak, LocalDate kraj) {
        if (pocetak.isBefore(LocalDate.now()) || kraj.isBefore(LocalDate.now())){
            System.err.println("Nije moguce uneti datum pre danasnjeg.");
            return false;
        }  
        if (kraj.isBefore(pocetak)) {
            System.err.println("Datum pocetka rezervacije ne moze biti posle datuma kraja rezervacije.");
            return false;
        }
        return true;
    }
    
    public static boolean proveraIDRezervacija(int ID){
        boolean dostupno = true;
                try {
                    provera.rezervisiVozilo(ID);
                } catch (VehicleNotFoundException | VehicleAlreadyRentedException e) {
                    System.err.println(e.getMessage());    
                    dostupno = false;
                }          
        return dostupno;
    }
    
    public static boolean daLiImaIznajmljenihVozila(){
        for (Vozilo v : provera.getVozila())
            if (!v.daLiJeDostupno())
                return true;
        return false;
    }

}