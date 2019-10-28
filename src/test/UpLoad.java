package test;

import MyUtils.DateUtils;

import java.io.*;

public class UpLoad {
    private static DateUtils myUtils = new DateUtils();
    private File file;

    public static void main(String[] args) throws Exception {
        UpLoad test = new UpLoad();
//        test.UpLoadFile("/Users/mac/DirForTest/newd1","new1");
//        test.UpLoadDir("/Users/mac/DirForTest/newd1");
            test.createNewFile("/Users/mac","test.txt");
            test.deleteFile("/Users/mac","test.txt");
        System.out.println(myUtils.representsCurrentCalendar());
    }

    /*上传文件需要文件父级路径*/
    public void UpLoadFile(String fileParent, String fileName) throws Exception {
        file = new File(fileParent, fileName);
        String writerName = new String("out_"+fileName);
        if (new File(fileParent+"_out",writerName).exists()) {
            System.out.println("已存在同名文件，请不要重复上传");
        } else {
            Reader reader = new FileReader(file);
//        byte[] bytes = new byte[512];
            Writer writer = new FileWriter(new File(fileParent+"_out",writerName));
            int i;
            while ((i = reader.read()) != -1) {
                writer.write(i);
            }
            System.out.println("上传成功！");
            reader.close();
            writer.flush();
            writer.close();
        }
    }

    /*上传文件夹及其附属文件，后期酌情添加if判断如果不存在父级目录时的文件夹创建*/
    public void UpLoadDir(String dirPath) throws Exception {
        file = new File(dirPath);
        if (!file.isDirectory())
            System.out.println("请上传正确格式的文件夹");
        else {
            String dirAbsolutePath = file.getPath();
            File dir = new File(dirAbsolutePath + "_out");
            dir.mkdir();
            System.out.println("文件夹创建成功！");
            for (File files :
                    file.listFiles()) {
                if(files.isDirectory())
                    UpLoadDir(files.getPath());
                else
                    UpLoadFile(files.getParent().toString(),files.getName().toString());
            }
        }
    }


    /*新建文件，需要文件名*/
    public void createNewFile(String fileParent, String fileName) throws IOException {
        file = new File(fileParent, fileName);
        boolean exists = file.exists();
        if (exists)
            System.out.println("无法重复创建已有的文件"+file.getPath());
            /*后期可以添加更人性化提示，建议是否删除原文件*/
        else {
            file.getParentFile().mkdirs();
            if(file.createNewFile())
                System.out.println("创建成功!");
            else
                System.out.println("出了点未知的小问题，请稍后重试");
        }
    }

    /*删除TxT格式的文件，需要文件名（不包含后缀）*/
    public void deleteFile(String fileParent, String fileName) {
        file = new File(fileParent, fileName);
        boolean is = file.isFile();
        if (is) {
            file.delete();
            System.out.println("删除成功！");
        } else
            System.out.println("删除失败，注意了，这是一个文件夹！");
    }


}
