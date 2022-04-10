import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockedMe {
    static String DIRECTORY;
    File folder_name;

    public LockedMe() {
        DIRECTORY = System.getProperty("user.dir");
        folder_name = new File(DIRECTORY+"/main");
        if (!folder_name.exists())
            folder_name.mkdirs();
        System.out.println("DIRECTORY : "+ folder_name.getAbsolutePath());
        System.out.println("\n(All operation will be performed in above directory) \n");
    }

    private static final String WELCOME_PROMPT =
    		"\n***********************************************************"+
    		"\n************* WELCOME TO LockedMe.com"+
                    "\n************* DEVELOPED BY RITIK"+
                    "\n***********************************************************"+
                    "\n\n         APPLICATION FEATURES :-\n"+
                    "\n * Listing of files present in saved folder (main)"+
                    "\n * Adding files"+
                    "\n * Deletion of files"+
                    "\n * Searching of files\n\n";

    private static final String MAIN_MENU_PROMPT =
            "\nMAIN MENU - Select your preffered operation: \n\n"+
                    "1 -> List Files in Directory\n"+
                    "2 -> Add, Delete or Search files\n"+
                    "3 -> Terminate Program";

    private static final String SECONDARY_MENU_PROMPT =
            "   \nSelect any of the following: \n\n"+
                    "   a -) Add a file\n"+
                    "   b -) Delete a file\n"+
                    "   c -) Search a file\n"+
                    "   d -) Go-Back";

    void showPrimaryMenu() {
        System.out.println(MAIN_MENU_PROMPT);
        try{
        	
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter your option:-");
            int option = scanner.nextInt();
            switch (option){
                case 1 : {
                    showFiles();
                    showPrimaryMenu();
                }
                case 2 : {
                    showSecondaryMenu();
                }
                case 3 : {
                    System.out.println("             (Program is now closed)                 \n\n"+
                "***************Thanks for using it***************");
                    System.exit(0);
                }
                default: showPrimaryMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please enter 1, 2 or 3");
            showPrimaryMenu();
        }
    }

    void showSecondaryMenu() {
        System.out.println(SECONDARY_MENU_PROMPT);
        try{
        	System.out.println("\nEnter your option:-");
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case 'a' : {
                    System.out.print("Adding a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim().toLowerCase();
                    addFile(filename);
                    break;
                }
                case 'b' : {
                    System.out.print("Deleting a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    deleteFile(filename);
                    break;
                }
                case 'c' : {
                    System.out.print("Searching a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    searchFile(filename);
                    break;
                }
                case 'd' : {
                    System.out.println("Going Back to MAIN menu\n");
                    showPrimaryMenu();
                    break;
                }
                default : System.out.println("Please enter a, b, c or d");
            }
            showSecondaryMenu();
        }
        catch (Exception e){
            System.out.println("Please enter a, b, c or d");
            showSecondaryMenu();
        }
    }

    void showFiles() {
        if (folder_name.list().length==0)
            System.out.println("\n*The folder is empty*");
        else {
            String[] list = folder_name.list();
            System.out.println("\nfiles in "+ folder_name +" are :-\n");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }

    void addFile(String filename) throws IOException {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equalsIgnoreCase(file)) {
                System.out.println("\n***File " + filename + " already present at " + folder_name);
                return;
            }
        }
        filepath.createNewFile();
        System.out.println("\n****File "+filename+" added to "+ folder_name);
    }

    void deleteFile(String filename) {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file) && filepath.delete()) {
                System.out.println("\n***File " + filename + " removed from " + folder_name);
                return;
            }
        }
        System.out.println("\n***Delete Operation failed. FILE NOT FOUND");
    }

    void searchFile(String filename) {
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file)) {
                System.out.println("\n***FOUND : File " + filename + " Located at " + folder_name);
                return;
            }
        }
        System.out.println("\n***File NOT found - Create One");
    }

    public static void main(String[] args) {
        System.out.println(WELCOME_PROMPT);
        LockedMe menu = new LockedMe();
        menu.showPrimaryMenu();
    }
}