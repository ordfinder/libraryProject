import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String n = null;
        LibraryManager manager = new LibraryManager();
        manager.init();
        do {//控制是否退出
            manager.menu();
            System.out.println("回主選單？y(是)/其他(結束)");
            n = input.next();
        }while ("y".equals(n));
    }
}

