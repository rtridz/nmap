package ru.bmstu.tp.nmapclient.Helper;

public class SpinnerParameters {
    String mode;
    String load;
    String scan;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public SpinnerParameters(String mode, String load, String scan) {
        this.mode = mode;
        this.load = load;
        this.scan = scan;
    }
}
