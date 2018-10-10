package com.clawhub.minibooksearch.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <Description> shell工具<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/27 <br>
 */
public class ShellUtil {
    /**
     * Run shell string.
     *
     * @param shpath the shpath
     * @return the string
     */
    public static String runShell(String shpath) throws IOException, InterruptedException {
        Process ps = Runtime.getRuntime().exec(shpath);
        ps.waitFor();
        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
