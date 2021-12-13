import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class LibraryManager {
    private Book[] book = new Book[10];//建立陣列儲存書本
    Scanner input = new Scanner(System.in);
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public void init(){//初始化
        Book book1 = new Book("《平凡的世界》",01,50,"賈平凹");
        Book book2 = new Book("《巴黎聖母院》",02,35,"雨果");
        Book book3 = new Book("《金陵十三釵》",03,26,"嚴歌苓");
        book[0] = book1;
        book[1] = book2;
        book[2] = book3;
    }

    public void menu(){//介面選擇
        System.out.println("-------歡迎進入圖書管理系統--------");
        System.out.println("請根據提示選擇:");
        System.out.println("1.增加圖書\t\t2.刪除圖書\t\t3.借閱圖書\n4.歸還圖書\t\t5.圖書借閱排行\t"+"6.書籍目錄\n"+"0.退出系統");
        int choice = input.nextInt();
        switch (choice){
            case 1:
                addBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                checkOutBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                rankBook();
                break;
            case 6:
                showBook();
                break;
            case 0:
                System.exit(0);
            default:
        }
    }

    public void showBook(){//輸出圖書
        for(Book x :book) {
            if (x!=null) {
                if (x.getStatc())
                    System.out.println("名稱：" + x.getName() +
                            "\t\t\t序列號：" + x.getId() + "\t\t作者：" + x.getAuthon() +
                            "\t\t被借"+x.getCount()+"次"+"\t\t狀態：入藏");
                else
                    System.out.println("名稱：" + x.getName() +
                            "\t\t\t序列號：" + x.getId() + "\t\t作者：" + x.getAuthon() +
                            "\t\t被借"+x.getCount()+"次"+"\t\t狀態："+format.format(x.getBorrowData())+"借出");
            }
        }
    }

    public void rankBook() {//按借閱次數降序排序
        Book[] tempbook = new Book[book.length];//建立一個臨時陣列使之等於原陣列
        for(int i = 0;i<book.length;i++){
            if(book[i] != null){
                tempbook[i] = book[i];
            }
        }
        System.out.println("借書次數由高到低為:");
        int i, j;
        for (i = 0;i < tempbook.length-1;i++) {//氣泡排序
            for (j = 0; j < tempbook.length - i - 1; j++) {
                if (tempbook[j] != null && tempbook[j + 1] != null) {
                    if (tempbook[j].getCount() < tempbook[j + 1].getCount()) {
                        Book temp = tempbook[j];
                        tempbook[j] = tempbook[j + 1];
                        tempbook[j + 1] = temp;
                    }
                }
            }
        }

        for (Book x : tempbook) {
            if (x != null) {
                if (x.getStatc())
                    System.out.println("名稱：" + x.getName() +
                            "\t\t序列號：" + x.getId() + "\t\t作者：" + x.getAuthon() +
                            "\t\t被借" + x.getCount() + "次" + "\t\t狀態：入藏");
                else
                    System.out.println("名稱：" + x.getName() +
                            "\t\t序列號：" + x.getId() + "\t\t作者：" + x.getAuthon() +
                            "\t\t被借" + x.getCount() + "次" + "\t\t狀態：" + x.getBorrowData() + "借出");
            }
        }
    }

    public void addBook(){//增加圖書
        String name,authon;
        int id,price,i;
        System.out.println("請輸入書籍名稱,序列號,價格,作者");
        name = input.next();
        id = input.nextInt();
        price = input.nextInt();
        authon = input.next();
        Book temp = new Book(name,id,price,authon);
        for(i = 0;i<book.length;i++){
            if(book[i]==null)
                break;
        }
        book[i] = temp;
    }

    public int findName(int location){//查詢與name相同的下標
        String name = input.next();
        for(int j = 0;j < book.length;j++){
            if(book[j].getName().equals(name)){
                location = j;
                break;
            }
        }
        return location;
    }

    public int findId(int location){//查詢與序列號相同的下標
        int n = input.nextInt();
        for(int j = 0;j < book.length;j++){
            if(book[j].getId()==n){
                location = j;
                break;
            }
        }
        return location;
    }

    public void deleteBook(){//刪除圖書
        System.out.println("1.按名稱刪除\t"+"2.按序列號刪除");
        int i = input.nextInt(),location = -1;//待刪除下標
        switch (i){
            case 1:
                System.out.println("請輸入要刪除的書籍名稱:");
//                String name = input.next();
//                for(int j = 0;j < book.length;j++){
//                    if(book[j].getName().equals(name)){
//                        location = j;
//                        break;
//                    }
//                }
                location = findName(location);
                break;
            case 2:
                System.out.println("請輸入要刪除書籍的序列號:");
//                int n = input.nextInt();
//                for(int j = 0;j < book.length;j++){
//                    if(book[j].getId()==n){
//                        location = j;
//                        break;
//                    }
//                }
                location = findId(location);
                break;
        }
        //進行移動
        int start = location,end;
        int nulllocation = book.length;//null的下標
        for(int j = 0;j<book.length;j++){
            if(book[j] == null) {
                nulllocation = j;
                break;
            }
        }
        end = nulllocation-1;
        for(int j = start;j < end;j++){
            if(j==book.length-1)
                book[j] = null;
            else
                book[j] = book[j+1];
        }
        book[end] = null;
    }

    public void checkOutBook(){//借閱圖書
        System.out.println("1.按名稱借閱\t"+"2.按序列號借閱");
        int i = input.nextInt(),location = -1;//待借閱下標
        switch (i){
            case 1:
                System.out.println("請輸入要借閱的書籍名稱:");
//                String name = input.next();
//                for(int j = 0;j < book.length;j++){
//                    if(book[j].getName().equals(name)){
//                        location = j;
//                        break;
//                    }
//                }
                location = findName(location);
                break;
            case 2:
                System.out.println("請輸入要借閱書籍的序列號:");
//                int n = input.nextInt();
//                for(int j = 0;j < book.length;j++){
//                    if(book[j].getId()==n){
//                        location = j;
//                        break;
//                    }
//                }
                location = findId(location);
                break;
        }

        book[location].setCount();//被借次數+1
        if(book[location].getStatc()){
            Date time = new Date();
            book[location].setBorrowData(time);
            book[location].setStatc(false);
        }else {
            System.out.println(book[location].getName()+"於"+book[location].getBorrowData()+"已借出！");
        }
    }

    public void returnBook(){//歸還圖書
        System.out.println("1.按書名歸還\t2.按序列號歸還");
        int i = input.nextInt(),location = -1;//待歸還下標
        switch (i){
            case 1:
                System.out.println("請輸入你要歸還的書名");
//                String name = input.next();
//                for(int j = 0;j < book.length;j++){
//                    if(book[j].getName().equals(name)){
//                        location = j;
//                        break;
//                    }
//                }
                location = findName(location);
                break;
            case 2:
                System.out.println("請輸入你要歸還的序列號");
//                int n = input.nextInt();
//                for(int j = 0;j < book.length;j++){
//                    if(book[j].getId()==n){
//                        location = j;
//                        break;
//                    }
//                }
                location = findId(location);
                break;
        }
        book[location].setStatc(true);
        book[location].setBorrowData(null);
    }
}