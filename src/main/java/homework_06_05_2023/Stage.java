package homework_06_05_2023;

public abstract class Stage {

    protected int length;
    protected String description;

    public abstract void go(Car c);

    public String getDescription() {
        return description;
    }

}
