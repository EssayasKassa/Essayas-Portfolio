import java.util.Scanner;

public class Driver {
    public static void main(String []args){
      Scanner in=new Scanner(System.in);
      DataBase dataBase= new DataBase();
      System.out.println("Enter from 0-7 do to the assigned tasks and -1 to Quit");
      int task=in.nextInt();
      Boolean loaded=false;
      while(task!=-1){
          if(task==0&&!loaded){
              dataBase.load();loaded=true;}

          if(loaded){
              if(task==1){
                  dataBase.printCount();
              }
              else if(task==2){
                  dataBase.printOldestAndRecent(dataBase.dataBase.stream());
              }
              else if(task==3){
                  dataBase.printByOrderOfReleaseForFrankenstein(dataBase.dataBase.stream());
              }
              else if(task==4){
                  dataBase.printChangeName(dataBase.dataBase.stream());
              }
              else if(task==5){
                  dataBase.associatingDirectorsToMovies(dataBase.dataBase.stream());
              }
              else if(task==6){
                  Scanner out= new Scanner(System.in);
                  System.out.println("Enter stage name ");
                  String stageName=out.nextLine();
                  System.out.println("Enter real name");
                  String realName=out.nextLine();
                  dataBase.alterName(stageName,realName);
              }
              else if(task==7){
                  dataBase.calculatePercentage();
              }

          }else{
              System.out.println("File as not been loaded choose 0 to load it!!!!");
          }
          System.out.println("Enter from 0-7 do to the assigned tasks and -1 to Quit");
          task=in.nextInt();
      }

    }

}
