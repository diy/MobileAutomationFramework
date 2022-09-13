package util;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class CommonUtils {

    /**
     * @param machineName
     * @return
     * @throws IOException
     */
    public Process getProcess(String machineName) throws IOException {
        String cmd = "$ANDROID_HOME/platform-tools/adb devices";
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (machineName.equalsIgnoreCase("Windows"))
            processBuilder.command("cmd.exe", "/c", cmd);
        else
            processBuilder.command("bash", "-c", cmd);
        Process process = processBuilder.start();
        return process;
    }

    /**
     * @return epoch Time in Miliseconds
     */
    public long getCurrentTimeStampInMili(){
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

    /**
     * Local time with Zone id e.g 'GMT+5:30' etc.
     */
    public LocalTime getCurrentTimeStampWithZoneId(String zoneTime){
        return LocalTime.now(ZoneId.of(zoneTime));
    }

    /**
     * concat string
     * @return
     */
    public String stringConcat(String toConcat){
        return String.join(toConcat);
    }
}
