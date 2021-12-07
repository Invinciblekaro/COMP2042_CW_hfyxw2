package com.cw.utils.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ConsoleFileStream
        extends PrintStream {
    private PrintStream source;

    public ConsoleFileStream(File file, PrintStream source) throws FileNotFoundException {
        super(new FileOutputStream(file, true));
        this.source = source;
    }


    public void print(String obj) {
        super.print(obj);
        this.source.println(obj);
    }


    public void print(Object obj) {
        super.print(obj);
        this.source.println(obj);
    }


    public void print(char obj) {
        super.print(obj);
        this.source.println(obj);
    }


    public void print(int obj) {
        super.print(obj);
        this.source.println(obj);
    }


    public void print(long obj) {
        super.print(obj);
        this.source.println(obj);
    }


    public void print(double obj) {
        super.print(obj);
        this.source.println(obj);
    }


    public void print(float obj) {
        super.print(obj);
        this.source.println(obj);
    }


    public void print(boolean obj) {
        super.print(obj);
        this.source.println(obj);
    }
}