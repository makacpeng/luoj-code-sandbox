package org.nanlu.luojcodesandbox.security;

import java.security.Permission;

/**
 *  默认安全管理器
 */
public class DefaultSecurityManager extends SecurityManager {

    /**
     *  检查所有权限
     * @param perm   the requested permission.
     */
    @Override
    public void checkPermission(Permission perm) {

        super.checkPermission(perm);
    }
}
