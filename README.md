# ACPCalculator

This is a Calculator program for a specific purpose. It is created to use internally between associated person.

Anyway my opinion is it can be adapt to **multi-purpose use**.

**What it does?**

The program will calculate expenses according to each person's proportional consumption. Store datas in cloud Database (MySQL) provided by Amazon Relational Database Service (RDS)

**Such as**

Person A --->   Advanced paid for 75%   / Consumed 25%.

Person B --->   Advanced paid for 0%    / Consumed 25%.

Person C --->   Advanced paid for 25%   / Consumed 25%.

Person D --->   Advanced paid for 0%    / Consumed 25%.

**The result given by the program are**

Person A --->   Received back for 50%   / Additional payment for 0%.

Person B --->   Received back for 0%    / Additional payment for 25%.   / Pay to Person A

Person C --->   Received back for 0%    / Additional payment for 0%.

Person D --->   Received back for 0%    / Additional payment for 25%.   / Pay to Person A



Created by Devler420.
