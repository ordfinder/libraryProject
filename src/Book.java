import java.util.Date;

public class Book {
    private String name;//書名
    private int id;//序列號
    private int price;//價格
    private String authon;//作者
    private boolean statc = true;//借閱狀態ture在館，false借出
    private Date borrowData;//借閱日期
    private int count;//被借次數

    public Date getBorrowData() {
        return borrowData;
    }

    public void setBorrowData(Date borrowData) {
        this.borrowData = borrowData;
    }

    public int getCount() {
        return count;
    }

    public void setCount() {
        this.count++;
    }

    public boolean getStatc() {
        return statc;
    }

    public void setStatc(boolean statc) {
        this.statc = statc;
    }

    public Book(String name, int id, int price, String authon) {//建構函式
        this.name = name;
        this.id = id;
        this.price = price;
        this.authon = authon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthon() {
        return authon;
    }

    public void setAuthon(String authon) {
        this.authon = authon;
    }
}