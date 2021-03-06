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
    // Initialize variables
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
    public void setiD (int iD) throws IllegalArgumentException
    {
        if (iD > 0)
            this.iD = iD;
        else
            throw new IllegalArgumentException("ID must be greater than zero");
    }    
    public void setName(String name)
    {
        this.name = name;
    }
    //Creates major, checks to see if its 3 length
    public void setMajor(String major) throws IllegalArgumentException
    {
        if (major.length() == 3)
        {
            this.major = major;
            this.major = this.major.toUpperCase();
        }
        else
        {
            throw new IllegalArgumentException("Please type in an appropriate 3 letter major");
        }
    }
    // compareTo method
    public int compareTo(student other)
    {
        int result;
        if (this.iD != 0 && other.iD != 0)
        {
            String otheriD = Integer.toString(other.iD);
            String currentiD = Integer.toString(this.iD);
            result = currentiD.compareTo(otheriD);
        }
        else if (this.name != null && other.name != null)
        {
            String current_name = this.getName().toLowerCase();
            String other_name = other.getName().toLowerCase();
            result = current_name.compareTo(other_name);
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
    
    public static void main(String[] args)
    {
        // Initialize variables and id counters
        String inName;
        String inMajor;
        String inID = "0";
        int id_counter = 1;
        //Initialize tree and add 5 students
        BinarySearchTree tree = new BinarySearchTree();
        student student = new student(id_counter, "Romeo", "adj");
        tree.add(student);
        id_counter++;
        student = new student(id_counter, "Juliet", "adj");
        tree.add(student);
        id_counter++;    
        student = new student(id_counter, "Adam", "dpr");
        tree.add(student);
        id_counter++;     
        student = new student(id_counter, "Eve", "cis");
        tree.add(student);
        id_counter++;
        student = new student(id_counter, "Tom", "cis");
        tree.add(student);
        id_counter++;
        tree.print();
        

        Scanner in = new Scanner(System.in);        
        boolean done = false;
        while(!done)
        {
            boolean search = false;
            boolean valid_input = false;
            // Prints out menu
            System.out.println("");
            System.out.println("A)dd R)emove S)earch P)rint Q)uit");
            String input = in.next().toUpperCase();
            // Terminates program
            if (input.equals("Q"))
            {
                tree.print();
                done = true;
                System.out.println("This program is terminated, this is the final tree.");
            }
            // Adds student and increment id counter while printing out tree
            else if (input.equals("A"))
            {
                /** If selecting to add, prompts for input, and saves input */ 
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter Student's Name: ");
                inName = scan.next();
                while (!valid_input)
                {
                    System.out.println("Enter Student's Major: ");
                    inMajor = scan.next();
                    if(inMajor.length() == 3)
                    {
                        student = new student(id_counter, inName, inMajor);
                        tree.add(student);
                        id_counter++;
                        tree.print();
                        valid_input = true;
                    }   
                    else
                    {
                        System.out.println("Please enter 3 letters for major.");
                    }
                }
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
            // Search based on input
            else if (input.equals("S"))
            {
                while (!search)
                {
                    System.out.println("Search for I)D or search by M)ajor?");
                    input = in.next().toUpperCase();
                    if (input.equals("I"))
                    {
                        Scanner scan = new Scanner(System.in);
                        while(!valid_input)
                        {
                            System.out.println("Enter student ID");
                            inID = scan.next();
                            // check to see if an integer
                            try
                            {
                                int check = Integer.parseInt(inID);
                                // check to see if greater than 0
                                try
                                {
                                    student.setiD(check);
                                    valid_input = true;
                                }
                                catch(IllegalArgumentException e)
                                {
                                    System.out.println("Input is not greater than 0.");
                                }
                            }
                            catch(NumberFormatException e)
                            {
                                System.out.println("Input is not an integer");
                            }
                        }    
                        int integer = Integer.parseInt(inID);
                        if (integer < 1)
                        {
                            System.out.println("Invalid student ID, please enter a number greater than 0.");
                        }
                        else if (integer > 0)
                        {                        
                            student = new student(integer);
                            if (tree.find(student) != null)
                            {
                                tree.printNode(tree.find(student));
                            }
                            else
                            {
                                System.out.println("There is no student with that ID");
                            }
                            search = true;
                        }
                        else
                        {
                            System.out.println("You did not input an integer.");
                        }                        
                    }
                    // Searches for major through input
                    else if (input.equals("M"))
                    {
                        Scanner scan = new Scanner(System.in);
                        while(!valid_input)
                        {
                            student = new student();
                            System.out.println("Enter student's major");
                            inMajor = scan.next();
                            try
                            {
                                student.setMajor(inMajor);
                                valid_input = true;
                            }
                            catch(IllegalArgumentException e)
                            {
                                System.out.println("Please input 3 characters as major.");
                            }
                            student.setMajor(inMajor);
                            tree.printMajor(student); 
                            search = true;
                        }
                    }
                    else 
                    {
                        System.out.println("Please enter a valid option: I)d or M)ajor");
                    }
                }   
            }
            else if (input.equals("P"))
            {
                tree.print();
            }
            else
            {
                System.out.println("Please enter a valid option:");
            }
        }
    }    
}
