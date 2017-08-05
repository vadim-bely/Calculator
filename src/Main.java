import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        Calculator calk = new Calculator();

        System.out.println("после каждого числа и знака ставить пробел");

        Scanner scn = new Scanner(System.in);

        String equation = scn.nextLine();
        if ("exit".equals(equation)) {
            return;
        }
//        System.out.println(splitEquation(equation));
        System.out.println(calk.execute(splitEquation(equation)));

        main(args);

    }

    public static String splitEquation(String equation) {
        char[] tmpMass = equation.toCharArray();
        String result = "";
        String num = "";

        for (char str : tmpMass) {
            if (str != ' ') {
                if (str == '*' || str == '/' || str == '+' || str == '-') {
                    result += num;
                    result += " ";
                    result += str;
                    result += " ";
                    num = "";

                } else if (str == '(' || str == ')'){
                    if(num != ""){
                        result += num;
                        result += " ";
                        num = "";
                    }
                    result += str;
                    if(str != ')') {
                    result += " ";
                    }

                } else {
                    num += str;
                }
            }
        }
        if (!num.equals("")) {
            result += num;

        }
        return  result;
    }
}
