package com.mstacey.springbootapi;

import org.testng.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener {
    
    private CustomScreenRecorder screenRecorder;


    public TestListener() {
        try {
            //this is the location that we are going to save the recorded file
            screenRecorder = new CustomScreenRecorder(new File(System.getProperty("user.dir") + "/target/screen-records"));
        } catch (IOException | AWTException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        try {
            screenRecorder.startRecording(iTestResult.getClass().getSimpleName() + "-" + iTestResult.getMethod().getMethodName(), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test PASSED " + iTestResult.getTestClass().getName() + " - " + iTestResult.getMethod().getMethodName());
        stopScreenRecording(true);
    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test FAILED " + iTestResult.getTestClass().getName() + " - " + iTestResult.getMethod().getMethodName());
        stopScreenRecording(true);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test SKIPPED " + iTestResult.getTestClass().getName() + " - " + iTestResult.getMethod().getMethodName());
        stopScreenRecording(false);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private void stopScreenRecording(boolean keepFile) {
        try {
            screenRecorder.stopRecording(keepFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}