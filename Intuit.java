
import java.io.*;
import java.nio.file.Path;

public class Intuit {

	public static void main(String[] args) throws IOException {
		// read the files from the directory
		
		String path = "";
		System.out.println("Enter the absolute path to the folder name ::");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
			path = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		String line = "";
		String cvsSplitBy = ",";
		int monthlyIncome = 0;
		int expenses = 0;
		boolean payCheck = false;
		int noOfPayChecks = 0;
		int expExceed = 0;
		String userName = " ";
		for (File file : listOfFiles) {
			if (file.isFile()) {
				if (file.getName().endsWith(".csv")) {
					noOfPayChecks = 0;
					expExceed = 0;
					int scoreGongOut = 0;
					int scoreAtHome = 0;
					int scoreTrans = 0;
					int scoreNightLife = 0;

					try {
						br = new BufferedReader(new FileReader(file));
						while ((line = br.readLine()) != null) {

							// ignore the lines with the feature names

							if (line.contains("auth_id") || line.contains("Date") || line.contains("Vendor")
									|| line.contains("Amount") || line.contains("Location"))

								continue;

							// use comma as separator
							String[] data = line.split(cvsSplitBy);
							userName = data[0];

							// check the monthly paychevk of the user
							if (data[2].contains("Paycheck")) {

								if (expenses > monthlyIncome) {
									expExceed++;
//									System.out.println("Expenses have exceeded monthlyIncome :" + monthlyIncome
//											+ "expenses :" + expenses + "expExceed" + expExceed);
								}

								expenses = 0;
								monthlyIncome = Integer.parseInt(data[3]);

								// check the number of paychecks received
								noOfPayChecks++;
								payCheck = true;
								continue;

							}
							if (payCheck) {

								// Calculate the expenses
								expenses += Math.abs(Float.parseFloat(data[3]));

							}
							
							/*
							 * Assigning scores depending on the transactions made by the user
							 * 
							 */

							if (data[2].contains("Movie") || data[2].contains("Restaurant")
									|| data[2].contains("Wedding"))
								scoreGongOut += 2;

							if (data[2].contains("On Demand TV") || data[2].contains("Food Delivery")
									|| data[2].contains("Pet Supply") || data[2].contains("Grocer"))
								scoreAtHome += 2;

							if (data[2].contains("Uber") || data[2].contains("Lyft")
									|| data[2].contains("Transportation") || data[2].contains("Taxi"))
								scoreTrans += 2;

							if (data[2].contains("Night Club") || data[2].contains("Concert") || data[2].contains("bar")
									|| data[2].contains("grill") || data[2].contains("Brewery"))
								scoreNightLife += 2;


						}

						System.out.println("For  user :" + userName);

						if (noOfPayChecks > 0 && (expExceed / noOfPayChecks) > 0.5) {
							//System.out.println("expExceed / noOfPayChecks : " + expExceed + ":" + noOfPayChecks);
							System.out.println("Likelihood of successful relationship : LOW");
							System.out.println("Reason: Poor financial planning");
						}

						else {
							/*
							 * Calculating total score 
							 *  0.8> med 1.5
							 *	1.5 > high
							 *	0.8< low
							 */

							double totalScore = (scoreGongOut + scoreAtHome) / ((scoreTrans + scoreNightLife) * 0.7);
							//System.out.println(" totalScore :  " + totalScore);
							//System.out.println("expExceed / noOfPayChecks : " + expExceed + ":" + noOfPayChecks);
							

							if (totalScore >= 1.5) {
								System.out.println("Likelihood of successful relationship : HIGH");
								System.out.println("Reason: High scores for parameters including -Movie,Restaurant,Wedding,Food Delivery,On Demand TV etc  ");
							} else if (totalScore >= 0.8 && totalScore < 1.5) {
								System.out.println("Likelihood of successful relationship : MED");
								System.out.println("Reason: Medium scores for parameters including -Movie,Wedding, Groceries, Transportation,Taxi, Night Life etc" );
							} else if (totalScore < 0.8) {
								System.out.println("Likelihood of successful relationship : LOW ");
								System.out.println("Reason: Medium scores for parameters including -Transportation, Bar,Night Clubs etc  ");
							}

						}

						System.out.println("-----------------------------------");

					} catch (FileNotFoundException e) {
						e.printStackTrace();

					}


				}

			}
		}
	}
}