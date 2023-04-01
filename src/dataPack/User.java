package dataPack;

import java.util.ArrayList;

public class User implements Data {
    private String name = "";
    private Coin coin = new Coin();
    private ArrayList<Rent> rents = new ArrayList<Rent>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, Coin coin) {
        this.name = name;
        this.coin = coin;
    }

    public String getName() {
        return this.name;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public double getCoin() {
        return this.coin.getCoin();
    }

    public ArrayList<Rent> getRents() {
        return this.rents;
    }

    public void topUp(int coin) {
        this.coin.posCoin(coin);
        ;
    }

    public void addRent(Rent rent) {
        this.rents.add(rent);
    }

    public boolean buyBook(Book book) {
        if (getCoin() - book.getPrice() >= 0 && book.getCount() >= 1) {
            coin.navCoin(book.getPrice());
            book.delBook(1);
            return true;
        }
        return false;
    }

    public void addRent(Book book) {
        this.rents.add(new Rent(book, this));
    }

    public boolean bookReturn(Rent r, int day) {
        if (getCoin() - r.calCost(day) >= 0) {
            coin.navCoin(r.calCost(day));
            rents.remove(r);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String rent = "";
        for (var i : rents)
            rent += i.toString() + ":";
        return name + ";" + coin.getCoin() + ";" + rent + ";";
    }

}
