package clase;

public class Livrator{
    private static String fullName = "Merealbe Briana";
    private static int varsta = 20;

    public Livrator(){}

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        Livrator.fullName = fullName;
    }

    public static int getVarsta() {
        return varsta;
    }

    public static void setVarsta(int varsta) {
        Livrator.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Nume livrator: " + fullName + "\n" +
                "Varsta Livrator: " + varsta + "\n";
    }
}
