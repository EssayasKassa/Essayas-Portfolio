import java.util.Iterator;
import java.util.Scanner;

public class Driver {
    public static void main(String []args){
        Scanner in = new Scanner(System.in);
        System.out.println("Thank you for choosing Georges parking please enter the length of Parking");
        int lengthOfStreet=in.nextInt();
        System.out.println("Please enter the length of georgescar");
        int lengthOfGeorgescar=in.nextInt();
        GeorgeLinkedStreet street= new GeorgeLinkedStreet(lengthOfStreet,lengthOfGeorgescar);

        System.out.println("Please enter P: to park a car    B:to block   R: to remove a car or Q to quit++");
        String command=in.next().toUpperCase();


        while(!command.equals("Q")){


            if(command.equals("P")){
                System.out.println("Please enter the length of your car");
                int lengthOfCar= in.nextInt();
                System.out.println("your ticket number is "+street.push(lengthOfCar));

            }
            else if(command.equals("B")){
                System.out.println("Please enter the length of your car");
                int lengthOfCar=in.nextInt();
                System.out.println(street.block(lengthOfCar));
            }
            else if(command.equals("R")){
                System.out.print("Please enter your ticket number");
                int ticketNumber= in.nextInt();
                street.remove(ticketNumber);
            }
            else{
                System.out.println("please enter a valid command");
            }
            System.out.println(street);
            System.out.println("Please enter P: to park a car    B:to block   R: to remove a car or Q to quit");
            command= in.next().toUpperCase();
        }


    }
}
