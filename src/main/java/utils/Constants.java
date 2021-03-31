package main.java.utils;

import java.io.File;

public interface Constants {
    String reportPath = System.getProperty("user.dir") + File.separator + "reports";
    String reportName = "Automation_Test_Report_";
    String applicationUrl = "http://demo.guru99.com/test/newtours/index.php";
    String screenshotsPath = System.getProperty("user.dir") + File.separator + "reports";
}
