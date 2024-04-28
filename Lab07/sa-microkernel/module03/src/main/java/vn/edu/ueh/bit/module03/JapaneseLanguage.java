package vn.edu.ueh.bit.module03;


import vn.edu.ueh.bit.core.Language;

public class JapaneseLanguage implements Language {

    @Override
    public String name() {
        return "Japanese";
    }

    @Override
    public String sayHello(String name) {
        return "Konichiwa, " + name;
    }
}