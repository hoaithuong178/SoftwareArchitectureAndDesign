package vn.edu.ueh.bit;

import vn.edu.ueh.bit.core.Language;

public class French implements Language {
    @Override
    public String sayHello(String name) {
        return "Bonjour "+name;
    }

    @Override
    public String name() {
        return "French";
    }
}
