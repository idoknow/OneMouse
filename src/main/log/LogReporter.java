package main.log;

import main.util.FileIO;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Records log during runtime,supports message types and can control the display types.
 * @author Rock Chin
 */
public class LogReporter {
    public static final byte TYPE_MESSAGE=0x01,TYPE_EXCEPTION=0x02,TYPE_WARNING=0x04,TYPE_DEBUG=0x08;
    public byte displayType=TYPE_MESSAGE|TYPE_EXCEPTION|TYPE_WARNING;

    public void setDisplayType(byte type){
        this.displayType=type;
    }
    public void addDisplayType(byte typeBit){
        this.displayType= (byte) (displayType|typeBit);
    }

    public static char ln='\n';

    //store log during this runtime,will not be clean until this runtime is destroyed.
    StringBuffer logBuffer=new StringBuffer();

    public void log(byte type,String s){
        logBuffer.append(s);
        if ((type&displayType)!=0x00){
            System.out.print(s);
        }
    }
    public void logln(byte type,String s){
        log(type,s+ln);
    }

    /**
     * Save log content to a file
     * @param filePath
     */
    public void dumpToFile(String filePath){
        try {
            FileIO.write(filePath,logBuffer.toString());
        }catch (Exception e){
            this.logln(TYPE_EXCEPTION,"Cannot save log to file:"+filePath+"\n"+getErrorInfo(e));
        }
    }
    /**
     * Get complete error msg from a exception obj
     * @param e exception
     * @return
     */
    public static String getErrorInfo(Exception e){
        StringWriter sw=new StringWriter();
        PrintWriter pw=new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString().replaceAll("\t","    ");
    }
}
