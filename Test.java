public class Test {
    public static void main(String[] args) {
        String s1 = new String("Java");
        String s2 = s1;
        System.out.print((s1 == s2) + " " + (s1.equals(s2)));
    }
}