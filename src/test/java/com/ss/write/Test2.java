package com.ss.write;

import java.awt.datatransfer.FlavorEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Test2 {

    public static void main(String[] args) throws IOException {
        File srcFile = new File("F:\\BaiduNetdiskDownload");

        new Test2().copyFile(srcFile,"F:\\test",srcFile.getAbsolutePath() + "\\");
    }

    private void copyFile(File srcFile, String targetRootPath,String rootPath) throws IOException {
        if( !srcFile.isDirectory() ){
            if( srcFile.getName().contains(".ev4")){
                File file = new File(targetRootPath + "\\" + srcFile.getAbsolutePath().replace(rootPath, "").replace("\\","  "));
                Files.copy(srcFile.toPath(),file.toPath());
            }
        }else {
            for( File src : srcFile.listFiles() ){
                copyFile(src,targetRootPath,rootPath);
            }
        }
    }
}
