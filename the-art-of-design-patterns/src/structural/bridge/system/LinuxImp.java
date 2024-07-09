package structural.bridge.system;

import structural.bridge.SystemImp;
import structural.bridge.Matrix;

/**
 * Linux 下图像展示实现类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class LinuxImp implements SystemImp {

    @Override
    public void doPaint(Matrix m) {
        System.out.println("在 Linux 操作系统显示图像");
    }
}
