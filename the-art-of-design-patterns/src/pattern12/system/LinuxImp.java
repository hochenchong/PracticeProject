package pattern12.system;

import pattern12.SystemImp;
import pattern12.Matrix;

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
