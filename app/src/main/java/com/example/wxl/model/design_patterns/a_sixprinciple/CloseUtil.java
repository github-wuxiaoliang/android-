package com.example.wxl.model.design_patterns.a_sixprinciple;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created on 2016/5/7.
 *
 * @author wuxiaoliang
 * @since 1.0
 */
public class CloseUtil {
    private CloseUtil() {
    }
    /*接口隔离：依赖抽象，不依赖具体实现，只需知道该对象是可以关闭的*/
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
