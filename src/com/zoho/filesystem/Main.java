package com.zoho.filesystem;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    /**
     * Creating a primitive file system for fun.
     *
     * Requirements:
     * Folder names in upper case
     * File names in lower case
     *
     * Commands:
     * For creating and managing files:
     * Add file:            addFile <filename>
     * Add hidden file:     addHiddenFile <filename>
     * Add folder:          mkdir <folder name>
     * Remove file/folder:  rm <file name>
     * Remove should be marked as deleted.
     *
     * Navigation:
     * go to a folder:      cd <folder name>
     * go to prev folder:   back
     *
     * Lists:
     * list files in a dir:         ls
     * list files, incl hidden:     la
     * list files, incl details:    ld
     * folder details + file count: info
     *
     * @param args
     */

    public static void main(String[] args) throws Exception{
	    //all commands go here.

        /**
         * plan: Create in memory structure for the file system.
         *
         * - when app starts, start at root dir: ZOHO
         * Folder class, File class
         * Folder class will have a list of folder and file objects.
         * Can access a file object using the name of the file or folder, so it has to be a List of Maps
         * Write filter methods for listing based on hidden or trashed files/folders
         * Folder and File may have different list of properties, so use different Classes for making the
         * permission list rigid
         *
         */
        System.out.println("Welcome to your new file system.");
        Folder root = new Folder("Zoho");
        Folder currentDir = root;
        Stack<Folder> path = new Stack<>();
        while(true){
            System.out.print(currentDir.getName()+"> ");
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            String[] input = command.split(" ");
            switch (input[0]){
                case "addFile":
                    if (input.length > 1){
                        FileSystem fs = new File(input[1]);
                        System.out.println(currentDir.addItemtoFolder(fs));
                    }
                    else{
                        System.out.println("Please enter file name");
                    }
                    break;

                case "addHiddenFile":
                    if (input.length > 1){
                        FileSystem fs = new File(input[1]);
                        FSProperties props = fs.getProperties();
                        props.setHidden(true);
                        fs.setProperties(props);
                        System.out.println(currentDir.addItemtoFolder(fs));
                    }
                    else{
                        System.out.println("Please enter file name");
                    }
                    break;

                case "mkdir":
                    if (input.length > 1){
                        FileSystem fs = new Folder(input[1]);
                        System.out.println(currentDir.addItemtoFolder(fs));
                    }
                    else{
                        System.out.println("Please enter file name");
                    }
                    break;

                case "rm":
                    if (input.length > 1){
                        System.out.println(currentDir.removeItemFromFolder(input[1]));
                    }
                    else{
                        System.out.println("Please enter file name");
                    }
                    break;

                case "ls":
                    System.out.println(currentDir.listContents());
                    break;

                case "la":
                    System.out.println(currentDir.listAllContents());
                    break;

                case "ld":
                    System.out.println(currentDir.listDetails());
                    break;

                case "le":
                    System.out.println(currentDir.listEverything());
                    break;

                case "recover":
                    if (input.length > 1){
                        String filename = input[1];
                        currentDir.getFile(filename).getProperties().setDeleted(false);
                        System.out.println(filename + " recovered successfully.");
                    }else{
                        System.out.println("Please enter file name...");
                    }
                    break;

                case "info":
                    System.out.println(currentDir.getInfo());
                    break;

                case "cd":
                    path.push(currentDir);
                    Folder subdir = currentDir.getSubDirectory(input[1]);
                    if (subdir != null){
                        currentDir = subdir;
                    }else{
                        System.out.println("No such directory");
                    }
                    break;
                case "back":
                    if (currentDir.equals(root) && path.empty()){
                        System.out.println("You are at the root directory, there is no parent!");
                    }else{
                        currentDir = path.pop();
                    }
                    break;
            }
        }

    }
}
