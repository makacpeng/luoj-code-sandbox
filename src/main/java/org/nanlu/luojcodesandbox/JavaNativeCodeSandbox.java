package org.nanlu.luojcodesandbox;

import org.nanlu.luojcodesandbox.model.ExecuteCodeRequest;
import org.nanlu.luojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 *   Java原生代码沙箱实现
 */
@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }

}
