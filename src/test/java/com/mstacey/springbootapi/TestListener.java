package com.mstacey.springbootapi;

import org.testng.*;
import java.io.File;


public class TestListener implements ITestListener {
    
    private CustomScreenRecorder screenRecorder;


    public TestListener() {
        try {
            screenRecorder = new CustomScreenRecorder(new File(System.getProperty("user.dir") + "/target/screen-records"));
        } catch (Exception e) {
            System.out.println("[ERROR] Could not create screen recorder" + e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        try {
            screenRecorder.startRecording(iTestResult.getClass().getSimpleName() + "-" + iTestResult.getMethod().getMethodName(), true);
        } catch (Exception e) {
            System.out.println("[ERROR]" + e.getMessage());
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
        stopScreenRecording(false);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test SKIPPED " + iTestResult.getTestClass().getName() + " - " + iTestResult.getMethod().getMethodName());
        stopScreenRecording(false);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test Failed but within accepted range" + iTestResult.getTestName());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Starting Test" + iTestContext.getName());

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finished Test" + iTestContext.getName());
    }

    private void stopScreenRecording(boolean keepFile) {
        try {
            screenRecorder.stopRecording(keepFile);
        } catch (Exception e) {
            System.out.println("[ERROR] Could not stop recording: " + e.getMessage());
        }
    }
}