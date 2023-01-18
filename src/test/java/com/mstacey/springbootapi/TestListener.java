package com.mstacey.springbootapi;

import org.testng.*;
import java.io.File;

/***
 * This whole class was stolen from the internet. Have tweaked a bit to meet requirements. Still needs tweaking. Very buggy
 * 
 * https://medium.com/@nisal444/screen-recorder-for-selenium-webdriver-with-testng-and-java-87287230ce7b 
 */
public class TestListener implements ITestListener {
    
    private CustomScreenRecorder screenRecorder;


    public TestListener() {
        try {
            screenRecorder = new CustomScreenRecorder(new File(System.getProperty("user.dir") + "/target/screen-records"));
        } catch (Exception e) {
            System.out.println("[ERROR] Could not create screen recorder" + e.getMessage());
        }
    }

    
    /** 
     * @param iTestResult
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        try {
            screenRecorder.startRecording(iTestResult.getClass().getSimpleName() + "-" + iTestResult.getMethod().getMethodName(), true);
        } catch (Exception e) {
            System.out.println("[ERROR]" + e.getMessage());
        }
    }

    
    /** 
     * @param iTestResult
     */
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test PASSED " + iTestResult.getTestClass().getName() + " - " + iTestResult.getMethod().getMethodName());
        stopScreenRecording(true);
    }


    
    /** 
     * @param iTestResult
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test FAILED " + iTestResult.getTestClass().getName() + " - " + iTestResult.getMethod().getMethodName());
        stopScreenRecording(false);
    }

    
    /** 
     * @param iTestResult
     */
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test SKIPPED " + iTestResult.getTestClass().getName() + " - " + iTestResult.getMethod().getMethodName());
        stopScreenRecording(false);
    }

    
    /** 
     * @param iTestResult
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test Failed but within accepted range" + iTestResult.getTestName());
    }

    
    /** 
     * @param iTestContext
     */
    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Starting Test" + iTestContext.getName());

    }

    
    /** 
     * @param iTestContext
     */
    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finished Test" + iTestContext.getName());
    }

    
    /** 
     * @param keepFile
     */
    private void stopScreenRecording(boolean keepFile) {
        try {
            screenRecorder.stopRecording(keepFile);
        } catch (Exception e) {
            System.out.println("[ERROR] Could not stop recording: " + e.getMessage());
        }
    }
}