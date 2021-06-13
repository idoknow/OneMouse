package test.log;

import main.log.LogReporter;

public class LogTest {
    public static void main(String[] args) {
        LogReporter logReporter=new LogReporter();
        logReporter.logln(LogReporter.TYPE_DEBUG,"Test");
        logReporter.addDisplayType(LogReporter.TYPE_DEBUG);
        logReporter.logln(LogReporter.TYPE_DEBUG,"Test2");

        logReporter.dumpToFile("logtest.txt");
    }
}
