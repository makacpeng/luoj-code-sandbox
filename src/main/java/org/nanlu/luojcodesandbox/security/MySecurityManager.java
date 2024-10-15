package org.nanlu.luojcodesandbox.security;

import java.security.Permission;

public class MySecurityManager extends SecurityManager {
    /**
     *  检查所有权限
     * @param perm   the requested permission.
     */
    @Override
    public void checkPermission(Permission perm) {
//        super.checkPermission(perm);
    }

    @Override
    public void checkRead(String file) {
//        System.out.println(file );
//        if (file.contains("/Users/chendepeng/Desktop/luoj-code-sandbox/src/main/java/org/nanlu/luojcodesandbox")) {
//            return;
//        }
//        throw new SecurityException("checkRead 权限异常" + file);
    }


    @Override
    public void checkWrite(String file) {
//        throw new SecurityException("checkWrite 权限异常" + file);
    }

    @Override
    public void checkExec(String cmd) {
        throw new SecurityException("checkExec 权限异常" + cmd);
    }

    @Override
    public void checkDelete(String file) {
//        throw new SecurityException("checkDelete 权限异常" + file);
    }

    @Override
    public void checkConnect(String host, int port) {
//        throw new SecurityException("checkConnect  权限异常" + host + ":" + port);
    }
}
