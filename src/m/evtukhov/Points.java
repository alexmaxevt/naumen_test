package m.evtukhov;

public class Points {

    private int x;
    private int y;
    private int weight;
    private boolean isStart;
    private boolean isFinish;

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setStart() {
        isStart = true;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setFinish() {
        isFinish = true;
    }

    public boolean isFinish() {
        return isFinish;
    }

    void IsFinish()
    {
        this.setStart();
    }
    void IsStart()
    {
        this.setFinish();
    }

    public Points (int x, int y, int weight) {
        setX(x);
        setY(y);
        setWeight(weight);
    }
}
