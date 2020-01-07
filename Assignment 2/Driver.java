/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esof_assignment_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author tybik
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File file = new File("output.txt"); //opens old file for deletion
        file.delete(); //deletes old file
        System.out.println("Available software");
        System.out.println("1. Relational");
        System.out.println("2. NoSQL");
        System.out.println("3. Graph");
        System.out.print("Enter your choice: "); 
        
        Scanner kb = new Scanner(System.in);
        int choice = kb.nextInt(); // choice
        DatabaseSoftware software; // initialize software
        
        switch(choice){ // create a storing method based on user input
            case 1:
                software  = new Relational(); // instanciate relational object
                break;
            case 2:
                software  = new NoSQL(); // instanciate NoSQL object
                break;
            case 3:
                software  = new Graph(); // instanciate Graph
                break;
            default:
                software = null; // if none selected, will instanciate a null object
                break;

        }
        
        software.performStore(); // software will store in its default strategy
        software.setStoreBehavior(new DocumentStore()); // change the default strategy to documentStore
        software.performStore(); // perform new store
    } 
}

abstract class DatabaseSoftware{
    StoreBehavior storeBehavior;
    
    public void performStore(){
        System.out.println("Calling assigned Store method");
        this.storeBehavior.Store(); // call method store in the storebehavior interface
    }
    
    public void setStoreBehavior(StoreBehavior sb){
        this.storeBehavior = sb; // set the current store strategy to the paramater sb
        System.out.println("Store behavior dynamically switched");
    }
}

interface StoreBehavior {
    
    public void Store(); // abstract method store 
}

class DocumentStore implements StoreBehavior{
    public void Store(){ // implements the documentStore
        System.out.println("Document store completed");
        try { //prints to file
            BufferedWriter out = new BufferedWriter(new FileWriter("output.txt", true));
            out.write("Document store completed\n");
            out.close();
        }
        catch (IOException e) {
        }
    }
}

class NodeStore implements StoreBehavior{
    public void Store(){ // implements the nodeStore
        System.out.println("Node store completed");
        try { //prints to file
            BufferedWriter out = new BufferedWriter(new FileWriter("output.txt", true));
            out.write("Node store completed\n");
            out.close();
        }
        catch (IOException e) {
        }
    }
}

class TableStore implements StoreBehavior{
    
    public void Store(){ // implements the tablestore
        System.out.println("Table store completed");
        try { //prints to file
            BufferedWriter out = new BufferedWriter(new FileWriter("output.txt", true));
            out.write("Table store completed\n");
            out.close();
        }
        catch (IOException e) {
        }
    }
}

class Graph extends DatabaseSoftware{
    
    public Graph(){
        this.storeBehavior = new NodeStore(); // default store to nodeStore
        System.out.println("New Graph software created, store behavior set to node store");
    }
}

class NoSQL extends DatabaseSoftware{
    
    public NoSQL(){ // constructor
        this.storeBehavior = new DocumentStore(); // creates the default store to DocumentStore
        System.out.println("New NoSQL software created, store behavior set to document store");
    }
}

class Relational extends DatabaseSoftware{
    
    public Relational(){
        storeBehavior = new TableStore(); // default store to tableStore
        System.out.println("New Relational software created, store behavior set to table store");
    }
}