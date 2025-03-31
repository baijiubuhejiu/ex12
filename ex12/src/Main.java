import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class ExceptionDemo {

    private double divisor;
    private double dividend;
    private double result;


    public void divide() throws InputMismatchException, ArithmeticException {
        Scanner input = new Scanner(System.in);
        try {

            System.out.println("请输入除数:");
            divisor = input.nextDouble();

            System.out.println("请输入被除数:");
            dividend = input.nextDouble();

            result = divisor / dividend;
        } finally {

            input.close();
        }
    }


    public void goToDivideMethod() throws InputMismatchException, ArithmeticException {
        divide();
    }


    public void displayChoices() {
        Scanner input = new Scanner(System.in);
        while (true) {

            System.out.println("1. 除法运算");
            System.out.println("2. 读取文件");
            System.out.println("3. 退出");

            try {
                int choice = input.nextInt();
                if (choice == 1) {
                    try {
                        goToDivideMethod();
                        System.out.println("除法结果: " + result);
                    } catch (InputMismatchException e) {
                        System.out.println("输入不匹配异常 " + e + " 发生。期望输入数字，但未提供");
                    } catch (ArithmeticException e) {
                        System.out.println("算术异常 " + e + " 发生。尝试除以零");
                    }
                } else if (choice == 2) {
                    try {
                        readAFile();
                    } catch (IOException e) {
                        System.out.println("文件读取异常 " + e + " 发生");
                    }
                } else if (choice == 3) {

                    break;
                } else {
                    System.out.println("无效选择，请重新输入");
                }
            } catch (InputMismatchException e) {
                System.out.println("输入不匹配异常 " + e + " 发生。期望输入数字，但未提供");

                input.nextLine();
            }
        }

        input.close();
    }


    public void readAFile() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入文件路径:");
        String filePath = input.nextLine();

        if (!filePath.contains(".")) {

            throw new MissingExtensionException("文件路径缺少扩展名");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            System.out.println("文件内容: " + line);
        } catch (FileNotFoundException e) {

            throw new FileNotFoundException("文件未找到: " + filePath);
        } finally {

            input.close();
        }
    }
}


class MissingExtensionException extends Exception {
    public MissingExtensionException(String message) {
        super(message);
    }
}


public class Main {
    public static void main(String[] args) {
        ExceptionDemo demo = new ExceptionDemo();
        demo.displayChoices();
    }
}