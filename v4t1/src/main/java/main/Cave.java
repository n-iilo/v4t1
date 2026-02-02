package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Cave implements Serializable {
    public Player player;
    private ArrayList<Monster> monsters;

    public Cave(Player player){
    this.player = player;
    this.monsters = new ArrayList<>();
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void listMonsters() {
        if (monsters.isEmpty()) {
            System.out.println("Luola on tyhjä.");
            return;
        }
        System.out.println("Luolan hirviöt:");
        int i = 1;
        for (Monster monster : monsters) {
             monster.printInfo(i);
             i++;
        }
    }

    public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }

    public Monster getMonster(int index) {
        index -= 1;
        if (index >= 0 && index < monsters.size()) {
            return monsters.get(index);
        } else {
            return null;
        }
    }

    public void saveGame(String fileName) {
        try {
            ObjectOutputStream gameWriter = new ObjectOutputStream(new FileOutputStream(fileName));
            gameWriter.writeObject(this);
            gameWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(String fileName) {
        try {
            ObjectInputStream gameLoader = new ObjectInputStream(new FileInputStream(fileName));
            Cave loaded = (Cave) gameLoader.readObject();
            this.player = loaded.player;
            this.monsters = loaded.monsters;
            gameLoader.close();
        } catch (IOException e) {
            e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
