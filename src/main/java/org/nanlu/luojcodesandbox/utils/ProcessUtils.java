package org.nanlu.luojcodesandbox.utils;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.nanlu.luojcodesandbox.model.ExecuteMessage;
import org.springframework.util.StopWatch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtils {

    /**
     *  执行进程获取信息
     * @param runProcess
     * @param opName
     * @return
     */
    public static ExecuteMessage runProcessAndGetMessage(Process runProcess, String opName) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int exitValue;
        try {
            exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (exitValue == 0) {
            //正常退出
            System.out.println(opName + "成功");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            List<String> outputStrList = new ArrayList<>();
            String runOutputLine;
            while (true) {
                try {
                    if ((runOutputLine = bufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                outputStrList.add(runOutputLine);
            }
            executeMessage.setMessage(StringUtils.join(outputStrList, "\n"));
        } else {
            //异常退出
            System.out.println(opName + "失败, 错误码" + exitValue);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            String runOutputLine;
            List<String> outputStrList = new ArrayList<>();
            while (true) {
                try {
                    if ((runOutputLine = bufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                outputStrList.add(runOutputLine);
            }
            executeMessage.setMessage(StringUtils.join(outputStrList, "\n"));
            BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
            String errorCompileOutputLine;
            List<String> errorOutputStrList = new ArrayList<>();
            while (true) {
                try {
                    if ((errorCompileOutputLine = errorBufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                errorOutputStrList.add(errorCompileOutputLine);
            }
            executeMessage.setErrorMessage(StringUtils.join(errorOutputStrList, "\n"));
        }
        stopWatch.stop();
        executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        return executeMessage;
    }

    /**
     *  执行交互式进程获取信息
     * @param runProcess
     * @param opName
     * @return
     */
    public static ExecuteMessage runInteractProcessAndGetMessage(Process runProcess, String opName, String args) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        OutputStream outputStream = runProcess.getOutputStream();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        try {
            String[] s = args.split(" ");
            outputStreamWriter.write(StrUtil.join("\n", s) + "\n");
            outputStreamWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputStream inputStream = runProcess.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder runOutputStringBuilder = new StringBuilder();
        String runOutputLine;
        while (true) {
            try {
                if ((runOutputLine = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            runOutputStringBuilder.append(runOutputLine);
        }
        executeMessage.setMessage(runOutputStringBuilder.toString());
        try {
            outputStream.close();
            inputStream.close();
            outputStreamWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        runProcess.destroy();
        return executeMessage;
    }

}
