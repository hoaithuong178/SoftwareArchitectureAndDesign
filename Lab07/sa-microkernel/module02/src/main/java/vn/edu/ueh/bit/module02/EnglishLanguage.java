package vn.edu.ueh.bit.module02;
import vn.edu.ueh.bit.core.Language;

public class EnglishLanguage implements Language {

    @Override
    public String name() {
        return "English";
    }

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}