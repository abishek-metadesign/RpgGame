package uk.co.metadesignsolutions;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String solve = solution.solve("D,T,X,F", "S,F,B");
        System.out.println(solve);
    }
}