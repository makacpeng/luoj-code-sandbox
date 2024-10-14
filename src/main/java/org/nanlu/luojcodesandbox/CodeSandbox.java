package org.nanlu.luojcodesandbox;


import org.nanlu.luojcodesandbox.model.ExecuteCodeRequest;
import org.nanlu.luojcodesandbox.model.ExecuteCodeResponse;

/**
 *  代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     *  执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
