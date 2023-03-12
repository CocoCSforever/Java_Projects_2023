# Cinema-Room-Manager
Objectives

1. Backgrounds

There are many enjoyable activities on this funny little planet Earth, and still, the happiest, simplest, and most fulfilling one is probably going to the movies. Going with friends, going with loved ones, experiencing a whole new adventure from the safety of a cinema seat, surrounded by like-minded fellow travelers.
As a beginner developer, you can contribute to creating this wonderful cinema experience. Your good friends asked you to help them create an application for a cinema theatre where people can get tickets, reserve seats, and enjoy their movie night.

2. Functions

(1) Visualize seating arrangement by printing the scheme to the console
(2) Buy tickets and display the current state of the seating arrangement when needed.
(3) A user can buy a ticket that has already been purchased by another user.
(4) The stats will show the current income, total income, the number of available seats, and the percentage of occupancy.
(5) The program should not stop after implementing one function.

3. Requirements

A. Overview:
At the start, your program should read two positive integer numbers that represent the number of rows and seats in each row. Then, it should print a menu with the following four items:

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit

· “Show the seats” should print the current seating arrangement. The empty seats should be marked with an “S” symbol, and taken seats are marked with a “B” symbol.
· “Buy a ticket” should read the seat coordinates from the input and print the ticket price like in the previous stage. After that, the chosen seat should be marked with a “B” when the item “Show the seats” is called. Notice it shouldn't allow a user to buy a ticket that has already been purchased.
If a user chooses an already taken seat, print “That ticket has already been purchased!” and ask them to enter different seat coordinates until they pick an available seat. Of course, you shouldn't allow coordinates that are out of bounds. If this happens, print “Wrong input!” and ask to enter different seat coordinates until the user picks an available seat.
· Statistics should print the following information.
The number of purchased tickets;
The number of purchased tickets represented as a percentage. Percentages should be rounded to 2 decimal places;
Current income;
The total income that shows how much money the theatre will get if all the tickets are sold.
· Exit should stop the program.

B. Show the seats
“Show the seats” should print the current seating arrangement. The empty seats should be marked with an “S” symbol, and taken seats are marked with a “B” symbol.

C. buy a ticket
When choosing a ticket you are guided not only by your space preference but also by your finances. Let's implement the opportunity to check the ticket price and see the reserved seat.
(1) Read two positive integer numbers that represent the number of rows and seats in each row and print the seating arrangement like in the first stage. Then, read two integer numbers from the input: a row number and a seat number in that row. These numbers represent the coordinates of the seat according to which the program should print the ticket price.
(2) The program shouldn't allow a user to buy a ticket that has already been purchased. If a user chooses an already taken seat, print “That ticket has already been purchased!” and ask them to enter different seat coordinates until they pick an available seat. Of course, you shouldn't allow coordinates that are out of bounds. If this happens, print “Wrong input”! and ask to enter different seat coordinates until the user picks an available seat.
(3) The ticket price is determined by the same rules as the previous stage:
If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half. Please note that the number of rows can be odd, for example, 9 rows. In this case, the first half is the first 4 rows, and the second half is the last 5 rows.
After that, the program should print out all the seats in the screen room as shown in the example and mark the chosen seat by the B symbol. Finally, it should print the ticket price.

D. Statistics
“Statistics” should print the following information:
(1) The number of purchased tickets;
(2) The number of purchased tickets represented as a percentage. Percentages should be rounded to 2 decimal places;
(3) Current income;
(4) The total income that shows how much money the theatre will get if all the tickets are sold. The ticket price is determined by the same rule.

E. Exit
“Exit” should stop the program.

F. Examples

The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Enter the number of rows:

> 6

Enter the number of seats in each row:

> 6

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 3

Number of purchased tickets: 0

Percentage: 0.00%

Current income: $0

Total income: $360

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 2

Enter a row number:

> 1

Enter a seat number in that row:

> 1

Ticket price: $10

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 3

Number of purchased tickets: 1

Percentage: 2.78%

Current income: $10

Total income: $360

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 2

Enter a row number:

> 1

Enter a seat number in that row:

> 1

That ticket has already been purchased!

Enter a row number:

> 10

Enter a seat number in that row:

> 20

Wrong input!

Enter a row number:

> 4

Enter a seat number in that row:

> 4

Ticket price: $10

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 1

Cinema:

  1 2 3 4 5 6

1 B S S S S S

2 S S S S S S

3 S S S S S S

4 S S S B S S

5 S S S S S S

6 S S S S S S

1. Show the seats
2. Buy a ticket
3. Statistics
4. Exit
> 3

Number of purchased tickets: 2

Percentage: 5.56%

Current income: $20

Total income: $360

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 4
# Cinema-Room-Manager