package it.fabrick.fabricktest.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RuntimeUtils {

    private RuntimeUtils() {}

    public static void logRuntimeProperties() {
        log.info(String.format("%-21s: %s", "maxMemory", humanReadableByteCount(Runtime.getRuntime().maxMemory(), true)));
        log.info(String.format("%-21s: %s", "availableProcessors", String.format("%10s CPU", Runtime.getRuntime().availableProcessors())));
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int    exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%10.1f %3s", bytes / Math.pow(unit, exp), pre + "B");
    }

}
