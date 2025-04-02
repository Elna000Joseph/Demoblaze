/**
 *  This is the class is developed to for capturing the screenshot and building the report
 * @author elna.joseph
 */
package reusefunctions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dataresource.TestDataResource;
import webresources.SeleniumActions;

public abstract class ReportScreenShot extends SeleniumActions implements ITestListener {

	public static  ExtentReports report;
	public static  ExtentTest logger;
	public static  String screenshotfilePath=null;
	public static  String testcaseName;
	static Date date = new Date();
	static String dateData = new SimpleDateFormat("ddMMyyyy").format(date);
	static String datetime = new SimpleDateFormat("ddMMyyyy_hh_ss").format(date);

	
	public static ExtentReports Instance(String suiteName) {
	
		report = new ExtentReports("Report/AutomationReport_"+dateData+"/"+suiteName+datetime+".html", true);
		report.loadConfig(new File("testdata/configReport.xml"));
		
		return report;
	}

	
	public static String screenShot(String status,String filename){
		try {
			File screenshotforElementnotPresent=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			filename = filename.replaceAll("[\\/:*?\"<>|]", " ");
			System.out.println(""+filename);
			screenshotfilePath = "Report/AutomationReport_"+(datetime.split("_"))[0]+"/Screenshot/"+testcaseName+"_"+datetime+"/"+status+"/"+filename+".png";
			FileUtils.copyFile(screenshotforElementnotPresent, new File(screenshotfilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenshotfilePath;
	}

	
	public static void report(LogStatus status,String message,String stepName, boolean screenShot) {
		{
			try{
				if(status.equals(LogStatus.FAIL))
				{
					screenshotfilePath = screenShot("Error", stepName);
					
					String[] filePathArray = screenshotfilePath.split("Report/AutomationReport_"+(datetime.split("_"))[0]+"/");
                    String filePath = "./"+filePathArray[1];
				
					logger.log(LogStatus.FAIL, stepName, message +logger.addScreenCapture(filePath));

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				
				}
				else if(status.equals(LogStatus.PASS))
				{
					logger.log(LogStatus.PASS, stepName, message);
			
				}
				else if(status.equals(LogStatus.ERROR)){
					screenshotfilePath = screenShot("Error", stepName);
					String filePath = System.getProperty("user.dir")+"/"+screenshotfilePath;
					logger.log(LogStatus.ERROR, stepName, message +logger.addScreenCapture(filePath));
				}
				else{
					logger.log(LogStatus.INFO, stepName, message);
				}

			}catch(NullPointerException e){
				System.out.println("Report :"+e);
			}
		}
	}


}
