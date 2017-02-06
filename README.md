# IntuitCode
====================
1. Compile Intuit .java file.

        javac Intuit.java 

2. Run the executable

        java Intuit

3. Program will ask for the following inputs.

         Path to the folder containing the userdata files.
         
Implementation Details
----------------

For the given data set, the transactions are analyzed. The monthly expenses is compared with the earnings of the user.
If the user exceeds his monthly expenses, than the total income we assume that the user has poor financial planning
and the likelihood of his relationship being stable might be low.

The transactions including the movies, restaurants, transporation, bars and grills, concerts, night clubs etc., are the parameters considered and the scores are assigned. Based on these scores we assign the likelihood of relationship as LOW, MED and HIGH.


Sample Output
============================
Enter the absolute path to the folder name ::
C:\\Users\\Ajith\\Downloads\\rit-challenge-master\\rit-challenge-master\\transaction-data
For  user :624
Likelihood of successful relationship : MED
Reason: Medium scores for parameters including -Movie,Wedding, Groceries, Transportation,Taxi, Night Life etc
________________________________
For  user :63891
Likelihood of successful relationship : LOW
Reason: Poor financial planning
________________________________
For  user :7299
Likelihood of successful relationship : MED
Reason: Medium scores for parameters including -Movie,Wedding, Groceries, Transportation,Taxi, Night Life etc
________________________________
For  user :56930
Likelihood of successful relationship : MED
Reason: Medium scores for parameters including -Movie,Wedding, Groceries, Transportation,Taxi, Night Life etc
________________________________
For  user :15248
Likelihood of successful relationship : LOW
Reason: Poor financial planning
________________________________
For  user :54144
Likelihood of successful relationship : LOW
Reason: Poor financial planning
________________________________
