import java.util.Scanner;
import java.lang.*;
import java.util.Iterator;
/**
 * Write a description of class student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class student implements Comparable<student>
{
    // instance variables
    private int iD;
    private String name;
    private String major;
    
    // Blank constructor, setting id to 0 to become consistent.
    public student()
    {
        this.iD = 0;
    }
    
    // Default constructor
    public student(int iD, String name, String major)
    {
        this.setiD(iD);
        this.setName(name);
        this.setMajor(major);
    }
    // Student id constructor
    
    public student(int iD)
    {
        this.iD = iD;
        this.name = null;
        this.major = null;
    }
    
    public int getiD()
    {
        return iD;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getMajor()
    {
        return major;
    }
    
    public void setiD(int iD)
    {
        this.iD = iD;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    //Creates major, checks to see if its 3 length
    public void setMajor(String major)
    {
        if (major.length() == 3)
        {
            this.major = major;
            this.major = this.major.toUpperCase();
        }
        else
        {
            System.out.println("Please type in an appropriate 3 letter major");
        }
    }
    // compareTo method
    public int compareTo(student other)
    {
        int result;
        if (this.name != null && other.name != null)
        {
            String current_name = this.getName().toLowerCase();
            String other_name = other.getName().toLowerCase();
            result = current_name.compareTo(other_name);
        }
        else if (this.iD != 0 && other.iD != 0)
        {
            String otheriD = Integer.toString(other.iD);
            String currentiD = Integer.toString(this.iD);
            result = currentiD.compareTo(otheriD);
        }
        else
        {
            result = this.major.compareTo(other.major);
        }
        return result;
    }
    // toString method
    public String toString()
    {
        System.out.println();
        return "Id: " + iD + " Name: " + name + " Major: " + major;
    }
    
    public Comparable find(Comparable obj, Node root)
    {
      Node current = root;
      while (current != null)
      {
         int d = current.data.compareTo(obj);
         if (d == 0) { return current; }
         else if (d > 0) { current = current.left; }
         else { current = current.right; }
      }
      return null;
    }
    
    public static void main(String[] args)
    {
        // Initialize variables and id counters
        String inName;
        String inMajor;
        String inID;
        int id_counter = 1;
        //Initialize tree and add 5 students
        BinarySearchTree tree = new BinarySearchTree();
        student student = new student(id_counter, "Romeo", "adj");
        id_counter++;
        tree.add(student);
        student = new student(id_counter, "Juliet", "adj");
        id_counter++;    
        tree.add(student);
        student = new student(id_counter, "Adam", "dpr");
        id_counter++;     
        tree.add(student);
        student = new student(id_counter, "Eve", "csi");
        id_counter++;
        tree.add(student);
        student = new student(id_counter, "Tom", "spa");
        id_counter++;
        tree.add(student);
        tree.print();
        
        Scanner in = new Scanner(System.in);
        
        boolean done = false;
        while(!done)
        {
            // Prints out menu
            System.out.println("");
            System.out.println("A)dd R)emove S)earch P)rint Q)uit");
            String input = in.next().toUpperCase();
            // Terminates program
            if (input.equals("Q"))
            {
                done = true;
                System.out.println("This program is terminated.");
            }
            // Adds student and increment id counter while printing out tree
            else if (input.equals("A"))
            {
                /** If selecting to add, prompts for input, and saves input */ 
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter Student's Name: ");
                inName = scan.next();
                System.out.println("Enter Student's Major: ");
                inMajor = scan.next();
                student = new student(id_counter, inName, inMajor);
                tree.add(student);
                id_counter++;
                tree.print();
            }
            // Removes student entered
            else if (input.equals("R"))
            {
                /** If selecting to remove, prompts for input, and removes based on input */  
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter Student's Name to be Removed: ");
                inName = scan.next();
                student = new student();
                student.setName(inName);
                tree.remove(student);
                tree.print();
            }
            else if (input.equals("S"))
            {
                System.out.println("Search for I)D or search by M)ajor?");
                input = in.next().toUpperCase();
                if (input.equals("I"))
                {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Enter student ID");
                    inID = scan.next();
                    int check = Integer.parseInt(inID);
                    student = new student(check);
                    tree.find(student);
                }
                else if (input.equals("M"))
                {
                    
                }
                else 
                {
                    System.out.println("Please enter a valid option: I)d or M)ajor");
                }
            }
            else if (input.equals("P"))
            {
                tree.print();
            }
            else
            {
                System.out.println("Please enter a valid option: A)dd R)emove P)rint Q)uit");
            }
        }
    }    
}
