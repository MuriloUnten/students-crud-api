package cli;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<MenuEntry> entries;
    private Scanner scanner;

    public Menu() {
        entries = new ArrayList<MenuEntry>();
        scanner = new Scanner(System.in);
    }

    public void pushEntry(MenuEntry entry) {
        entries.add(entry);
    }

    public void createEntry(String name, Executable hook) {
        MenuEntry entry = new MenuEntry(name);
        entry.setAction(hook);
        pushEntry(entry);
    } 

    private void executeEntry(int index) {
        entries.get(index).execute();
    }

    public void run() {
        boolean exit = false;
        int option = entrySelector();

        while (!exit) {
            if (option == 0) {
                exit = true;
                break;
            }
            executeEntry(option);
            option = entrySelector();
        }
    }

    // Awful but works for now
    private int entrySelector() {
        System.out.println("\n--------------------");
        printEntries();
        System.out.println("--------------------");
        System.out.print("Escolha uma opção: ");

        int option = -1;
        while (option <= -1 || option >= entries.size()) {
            try {
                option = scanner.nextInt();

                if (option <= -1 || option >= entries.size()) {
                    throw new Exception("Invalid option");
                }
            }
            catch (Exception e) {
                System.out.println(e);
                System.out.print("Opção inválida. Escolha novamente (Ex. 1, 2, 3): ");
                option = -1;
            }
        }
        return option;
    }
 
    private void printEntries() {
        int i = 0;
        for (MenuEntry e : entries) {
            System.out.println(i++ + ". " + e.getName());
        }
    }
}
