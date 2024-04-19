package org.example;

import jdepend.xmlui.JDepend;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter printWriter=new PrintWriter("report.xml");
        JDepend jDepend=new JDepend(printWriter);
        jDepend.addDirectory("D:\\4_1\\www\\SpringData");
        jDepend.analyze();
        ProcessBuilder builder = new ProcessBuilder(new String[]{
                "cmd.exe",
                "/c",
                "npm run jdepend-ui D:\\4_2\\KienTrucPhanMem\\KienTrucTuan4\\untitled\\report.xml org"});
        builder.directory(new File("D:\\4_2\\KienTrucPhanMem\\KienTrucTuan4\\jdepend-ui"));
        builder.start();


        Runtime.getRuntime().exec(new String[]{
                "cmd.exe",
                "/c",
                "start \"\" \"D:\\4_2\\KienTrucPhanMem\\KienTrucTuan4\\jdepend-ui\\index.html\""});
    }
}