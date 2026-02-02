package main;


import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Syötä pelaajan nimi: ");
        String name = scanner.nextLine();
        Player player = new Player(name);
        Cave cave = new Cave(player);
        
        String fileName;
        
        boolean exit = false;
        while (!exit) {
            System.out.println("1) Lisää luolaan hirviö");
            System.out.println("2) Listaa hirviöt");
            System.out.println("3) Hyökkää hirviöön");
            System.out.println("4) Tallenna peli");
            System.out.println("5) Lataa peli");
            System.out.println("0) Lopeta peli");

            String input = scanner.nextLine();
            int i = Integer.parseInt(input);

            switch(i) {
                case 1:
                    System.out.println("Anna hirviön tyyppi: ");
                    String type = scanner.nextLine();

                    System.out.println("Anna hirviön elämän määrä numerona: ");
                    int health = scanner.nextInt();
                    scanner.nextLine();

                    Monster monster = new Monster(type, health);
                    cave.addMonster(monster);
                    break;
                case 2:
                    cave.listMonsters();
                    break;
                case 3:
                    System.out.println("Valitse hirviö, johon hyökätä:");
                    cave.listMonsters();
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    Monster target = cave.getMonster(choice);
                    if (target != null) {
                        cave.player.attack(target);
                    } else {
                        System.out.println("Ei validi hirviön numero.");
                        break;
                    }
                    if (target.health <= 0) {
                        cave.removeMonster(target);
                    }
                    break;

                case 4:
                    System.out.println("Anna tiedoston nimi, johon peli tallentaa:");
                    fileName = scanner.nextLine();
                    cave.saveGame(fileName);
                    System.out.println("Peli tallennettiin tiedostoon " + fileName + ".");
                    break;
                
                case 5:
                    System.out.println("Anna tiedoston nimi, josta peli ladataan:");
                    fileName = scanner.nextLine();
                    cave.loadGame(fileName);
                    System.out.println("Peli ladattu tiedostosta " + fileName + ". Tervetuloa takaisin, " + player.name + ".");
                    break;
                
                case 0:
                    System.out.println("Peli päättyy. Kiitos pelaamisesta!");
                    exit = true;
                    break;
            }
        }
        scanner.close();
    }
}


