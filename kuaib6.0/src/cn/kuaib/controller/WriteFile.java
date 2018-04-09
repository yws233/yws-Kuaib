package cn.kuaib.controller;

import java.io.*;

public class WriteFile {
    static void main(String args[]) {
        /*try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            *//* 读入TXT文件 *//*
            String pathname = "statics"+File.separator+"uploadfiles" + File.separator + "1516254288986_Personal.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            while (line != null) {
                line = br.readLine(); // 一次读入一行数据
            }

            *//* 写入Txt文件 *//*
            File writename = new File("statics"+File.separator+"uploadfiles" + File.separator + "out.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("我会写入文件啦\r\n"); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
        /*try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            *//* 读入TXT文件 *//*
            String pathname = user.getUpic(); // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            Writer writer = null;
            BufferedWriter bufferedWriter = null;
            while (line != null) {
                line = br.readLine(); // 一次读入一行数据
                logger.info("*******line:"+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + line);
                *//* 写入Txt文件 *//*
                String outPath = "/home/yws/pratices/Kuaib/out/artifacts/Kuaib_war_exploded/statics/uploadfiles/out.txt";
                File file = new File(outPath);
                writer = new FileWriter(file);
                bufferedWriter = new BufferedWriter(writer);
                //向文件写入信息
                bufferedWriter.write("写入成功！" + line);
            }
            writer.flush(); // 把缓存区内容压入文件
            writer.close(); // 关闭文件
            logger.info("》》》》》》写入文件成功！！！！！！！！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    }
