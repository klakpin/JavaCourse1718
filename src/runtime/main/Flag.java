package runtime.main;

public class Flag {

    public boolean value;

    public Flag(boolean b) {
        value = b;
    }

    public boolean value() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
