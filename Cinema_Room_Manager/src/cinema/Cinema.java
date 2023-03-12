package cinema;

import java.util.Scanner;

public class Cinema {
    char[][] seats;
    Scanner scanner = new Scanner(System.in);
    int row,seat,purchasedTickets=0,currentIncome=0,totalIncome=0;
    boolean big;
    String percentage="0.00";

    public static void main(String[] args) {
        // Write your code here
        Cinema cinema=new Cinema();
        cinema.printMenu();
        while(cinema.scanner.hasNext()){
            int choice = cinema.scanner.nextInt();
            if(choice==1){
                cinema.showSeats();
            }else if(choice==2){
                cinema.buyTicket();
            }else if(choice==3) {
                cinema.statistics();
            }else{
                break;
            }
            cinema.printMenu();
        }
        //System.out.println("Total income:\n" + "$" + calculate(a, b));
    }

    public Cinema(){
        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seat = scanner.nextInt();
        totalIncome=calculate(row,seat);
        seats = new char[row+1][seat+1];
        if(row*seat>60){
            big=true;
        }else
            big=false;

        for (int i = 0; i <=row; i++) {
            for (int j = 0; j <= seat; j++) {
                if (j == 0 && i == 0) {
                    seats[i][j] = ' ';
                } else if (i == 0) {
                    seats[i][j] = (char) ('0' + j);
                } else if (j == 0) {
                    seats[i][j] = (char) ('0' + i);
                } else {
                    seats[i][j] = 'S';
                }
            }
        }
    }
public void printMenu(){
    System.out.println();
    System.out.println("1. Show the seats\n" +
            "2. Buy a ticket\n" +
            "3.Statistics\n"+
            "0. Exit");
}
public void showSeats(){
    System.out.println();
    System.out.println("Cinema:");
    for (int i = 0; i <=row; i++) {
        for (int j = 0; j <=seat; j++) {
            System.out.print(seats[i][j] + " ");
        }
        System.out.println();
    }
}


    public void buyTicket(){
        System.out.println();
        System.out.println("Enter a row number:");
        int c = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int d = scanner.nextInt();
        System.out.println();
        while(c>row || d>seat || seats[c][d]=='B'){
            if(c>row || d>seat){
                System.out.println("Wrong input!\n");
            }else if(seats[c][d]=='B'){
                System.out.println("That ticket has already been purchased!\n");
            }
            System.out.println("Enter a row number:");
            c = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            d = scanner.nextInt();
            System.out.println();//maybe consider placing it at first
        }
        if (!big) {
            System.out.println("Ticket price: $" + 10);
            currentIncome+=10;
        } else if (c <= row / 2) {
            System.out.println("Ticket price: $" + 10);
            currentIncome+=10;
        } else {
            System.out.println("Ticket price: $" + 8);
            currentIncome+=8;
        }
        seats[c][d]='B';
        purchasedTickets+=1;
        percentage=String.format("%.2f",((double)100)*purchasedTickets/row/seat);
    }
public void statistics(){
    System.out.println();
    System.out.printf("Number of purchased tickets: %d\n" +
                    "Percentage: "+percentage+"%%\n" +
                    "Current income: $%d\n" +
                    "Total income: $%d\n",
            purchasedTickets,currentIncome,totalIncome);


}
      public static int calculate(int a,int b){
          if(a*b<=60){
              return 10*a*b;
          }else{
              return (a/2*10+(a-a/2)*8)*b;
          }
        }


            /*for(int j=1;j<9;j++){
                seats[0][j]=(char)j;
            }
            for(int i=1;i<8;i++){
            seats[i][0]=(char)i;
            }

            for(int i=1;i<8;i++){
            for(int j=1;j<9;j++){
                seats[i][j]='S';
            }
            }
            for(int i=0;i<8;i++){
                System.out.println(seats[i]);
            }*/

    }