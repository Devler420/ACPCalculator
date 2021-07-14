# ACPCalculator

(ACP = A Calculator Program)

This is a Calculator program for a specific purpose. It is created to use internally between associated person. Anyway my opinion is it could be adapt to **multi-purpose use**.

Written in **Java**.

**What it does?**

Basically, the program is capable of calculating expenses for each person participating in an event. Such as there are 4 people going camping, picknick.
Everyone bought their own foods, drinks. Maybe someone paid the car rental or someone paid for the hotel or camping ground, a tent. So each of them have their own expenses and at the end of the event they are going to have to calculate how much money they have to
pay someone or receive back from someone. That's where the program came to be helpful.

So it's very simple. This is for the case that you split the bill "EQUALLY". All you have to do is enter the name of the product (you have bought), the price, and name of person who bought it.
Then the program will automatically calculate the rest. The results are such as Person A pays 50 baht to Person B, Person C receives back 100 baht from Person A.
And the results are also stored in a cloud database so you can track your expenses history.

**Example of results**

Person A --->   Advanced paid for 75%   / Consumed 25%.

Person B --->   Advanced paid for 0%    / Consumed 25%.

Person C --->   Advanced paid for 25%   / Consumed 25%.

Person D --->   Advanced paid for 0%    / Consumed 25%.

**The result given by the program are**

Person A --->   Received back for 50%   / Additional payment for 0%.

Person B --->   Received back for 0%    / Additional payment for 25%.   / Pay to Person A

Person C --->   Received back for 0%    / Additional payment for 0%.

Person D --->   Received back for 0%    / Additional payment for 25%.   / Pay to Person A

**Simple GIF demonstrate the program.**

![Demo GIF](https://github.com/Devler420/ACPCalculator/blob/main/Animated%20GIF-downsized_large.gif)

**Sample pictures of GUI**

**1. LoginFrame**

Login frame where the program will also check for the latest version besides from user & password matching.

![Login-Frame](https://github.com/Devler420/ACPCalculator/blob/main/1LoginFrame.JPG)

**2. MainFrame**

Main frame which leads to all of the functions available.

![Main-Frame](https://github.com/Devler420/ACPCalculator/blob/main/2MainFrame.JPG)

**3. DatabaseResultFrame**

Displays all the data from cloud database by executing SQL command in the backend.

![DatabaseResult-Frame](https://github.com/Devler420/ACPCalculator/blob/main/3MainResultFrame.JPG)

**4. CalculatorFrame**

Main function of the program where users enter raw data then the program process into result including with storing data in the database.

![Calculator-Frame](https://github.com/Devler420/ACPCalculator/blob/main/4CalculateFrame.JPG)

**5. ReportFrame**

Displays specific data where users can either enter the Name of who they want to search or just the Date range. Then the program execute SQL command according to user's input to pull out the data they want from server.

![Report-Frame](https://github.com/Devler420/ACPCalculator/blob/main/5reportFrame.JPG)

**6. UserManagementFrame (Admin)**

Typical Users Management frame for admin to manage.

![UserManagement-Frame](https://github.com/Devler420/ACPCalculator/blob/main/6UserFrame.JPG)

Created by Devler420.
