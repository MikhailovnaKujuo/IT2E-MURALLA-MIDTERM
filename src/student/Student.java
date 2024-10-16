package student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Student {
    Config conf = new Config();
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Student st = new Student();
        
        int opt;
        do{
            try{
                System.out.println("\t  STUDENT ENTITY\n");
                System.out.println("1. Create\n"
                        + "2. Read\n"
                        + "3. Update\n"
                        + "4. Delete\n"
                        + "5. Exit\n");

                System.out.print("Option: ");
                opt = scan.nextInt();
                switch(opt){
                    case 1:
                        st.create();
                        break;
                    case 2: 
                        System.out.println("\tREAD\n");
                        st.read();
                        break;
                    case 3:
                        st.update();
                        break;
                    case 4:
                        st.delete();
                        break;
                    default:
                        System.out.println("Invalid Input.");
                }
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------");
                
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scan.nextLine(); 
                opt = -1; 
            }
        }while(opt != 5);
        
    }
     
    public void create(){
        System.out.println("\tCREATE");
        Scanner scan = new Scanner(System.in);
        
        System.out.println("\nEnter Student Details:");
        
        System.out.print("\nName: ");
        String name = scan.nextLine();
        
        System.out.print("Age: ");
        int age = scan.nextInt();
        scan.nextLine();
        
        System.out.print("Email: ");
        String email = scan.nextLine();
        
        System.out.print("Address: ");
        String address = scan.nextLine();
        
        System.out.print("Phone Number: ");
        String phone_number = scan.nextLine();
        
        System.out.println("");
        String sql = "INSERT INTO students (name, age, email, address, phone_number) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, name, age, email, address, phone_number);
    }
    
    public void read(){
        
        String[] headers = {"ID", "Name", "Age", "Email", "Address", "Phone Number"};
        String[] columns = {"id", "name", "age", "email", "address", "phone_number"};
        String sql = "SELECT * FROM students";
        
        System.out.println("");
        conf.viewRecords(sql, headers, columns);
        
    }
    
    public void update(){
        System.out.println("\tUPDATE");
        Scanner scan = new Scanner(System.in);
        
        read();
        System.out.println("");
        
        int id;        
        boolean idExists;
        do{
            System.out.print("Student ID you want to Update: ");
            id = scan.nextInt();
            
            idExists = conf.doesIDExist("students", id);
            if(!idExists){
                System.out.println("Student ID Doesn't Exist.\n");
            }
            
        }while(!idExists);
        scan.nextLine();
        
        System.out.println("Enter New Student Details:");
        
        System.out.print("\nNew Name: ");
        String name = scan.nextLine();
        
        System.out.print("New Age: ");
        int age = scan.nextInt();
        scan.nextLine();
        
        System.out.print("New Email: ");
        String email = scan.nextLine();
        
        System.out.print("New Address: ");
        String address = scan.nextLine();
        
        System.out.print("New Phone Number: ");
        String phone_number = scan.nextLine();
        
        System.out.println("");
        String sql = "UPDATE students SET name = ?, age = ?, email = ?, address = ?, phone_number = ? WHERE id = ?";
        conf.updateRecord(sql, name, age, email, address, phone_number, id);
    }
    
    public void delete(){
        System.out.println("\tDELETE\n");
        Scanner scan = new Scanner(System.in);
        
        read();
        System.out.println("");
        
        System.out.print("Student ID you want to DELETE: ");
        int id = scan.nextInt();
        
        String sql = "DELETE FROM students WHERE id = ?";
        conf.deleteRecord(sql, id);
    }
    
}
