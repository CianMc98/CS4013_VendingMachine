public class Operator {

    private String passcode;
    private int type;

    public Operator(String passcode, int type) {
        this.passcode = passcode;
        this.type = type;
    }

    public Operator() {
        passcode = "0000";
        type = 0;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}