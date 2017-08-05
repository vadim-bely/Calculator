

public class Calculator {



    public double execute(String equation) {
        return sumEquation(priorityProcessing(parenthesisPriority(equation)));
    }



    /**
     * Выполняет приоритетные операции (умножение и деление)
     *
     * @param equationMass массив строк состоящий из заданного уравнения
     * @return массив строк с выполненными приоритетными операциями
     */
    private String[] priorityProcessing(String[] equationMass) {
        String[] result = new String[equationMass.length];

        int count = 0;
        String lastNum = null;
        String lastSymbol = null;

        for (int i = 0; i < equationMass.length; i++) {
            String str = equationMass[i];

            if (lastNum == null) {
                lastNum = str;
            } else if (str.equals("*") || str.equals("/")) {
                lastSymbol = str;
            } else if (lastSymbol != null) {
                Double preNum = Double.parseDouble(lastNum);
                Double postNum = Double.parseDouble(str);
                lastNum = Double.toString(dataProcessing(preNum, postNum, lastSymbol));
                lastSymbol = null;
            } else {
                result[count++] = lastNum;
                result[count++] = str;
                lastNum = null;
            }

            if (i == equationMass.length - 1) {
                result[count] = lastNum;
            }
        }

        equationMass = new String[count + 1];
        System.arraycopy(result, 0, equationMass, 0, count + 1 );

        return equationMass;
    }

    /**
     * Выполняет операцию с переданными числами
     *
     * @param firstNum первое число
     * @param secondNum второе число
     * @param operationSymbol операция, которую необходимо выполнить
     * @return результат операции
     */
    private double dataProcessing(double firstNum, double secondNum, String operationSymbol) {
        switch (operationSymbol) {
            case "-": return firstNum - secondNum;
            case "+": return firstNum + secondNum;
            case "*": return firstNum * secondNum;
            case "/": return firstNum / secondNum;
        }
        return 0;
    }

    /**
     * Выполняет операции низкого приоритета (сложение и вычитание)
     *
     * @param equationMass массив строк состоящий из заданного уравнения
     * @return результат выражения
     */
    private double sumEquation(String[] equationMass) {
        String lastNum = null;
        String lastSymbol = null;

        for (String str : equationMass) {
            if (lastNum == null) {
                lastNum = str;
            } else if (lastSymbol == null) {
                lastSymbol = str;
            } else if (lastSymbol.equals("+") || lastSymbol.equals("-")) {
                Double preNum =  Double.parseDouble(lastNum);
                Double postNum =  Double.parseDouble(str);
                lastNum = Double.toString(dataProcessing(preNum, postNum, lastSymbol));
                lastSymbol = null;
            }
        }
        return Double.parseDouble(lastNum);
    }

    /**
     * Выполняет вычисление выражений внутри скобок
     *
     * @param equation выражение
     * @return массив строк  выполненными выражениями внутри скобок.
     */
    private String[] parenthesisPriority(String equation) {
        String[] equationMass = equation.split(" ");
        String[] result = new String[equationMass.length];
        String[] parenthesisMass = new String[equationMass.length - 2];
        boolean brace = false;
        int countParenthesis = 0;
        int countResult = 0;

        for (String value : equationMass) {
            if (value.equals("(")){
                brace = true;
            } else if (value.equals(")")) {
                brace = false;

                String[] tmpMass = new String[countParenthesis];
                System.arraycopy(parenthesisMass, 0, tmpMass, 0, countParenthesis);

                result[countResult] = Double.toString(sumEquation(priorityProcessing(tmpMass)));
                countResult++;
                countParenthesis = 0;
                parenthesisMass = new String[equationMass.length - 2];
            } else if (brace) {
                parenthesisMass[countParenthesis] = value;
                countParenthesis++;
            } else {
                result[countResult] = value;
                countResult++;
            }
        }

        equationMass = new String[countResult];
        System.arraycopy(result, 0, equationMass, 0, countResult);
        return equationMass;
    }
}
