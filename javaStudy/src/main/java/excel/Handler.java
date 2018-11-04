package excel;

import java.io.File;

/**
 * @author linqw
 */
public interface Handler {

    /**
     * 生成文件
     * @param flag 处理标志
     * @param sourceFile 源file
     * @param targetFile 目标file
     */
    void operator(int flag, File sourceFile, File targetFile);

}
