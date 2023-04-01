package dataPack;

public class Tag implements Data {
    private String name = "";

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }

}
