package Task1;

public class split {
    public static void main(String[] args) {
        String input = "A3 to B4";
        String[] split = input.split(" to ");

        System.out.println(split[0]);
        System.out.println(split[1]);
    }
}
