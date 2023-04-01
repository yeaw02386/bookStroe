package dataPack;

public class Coin {
    double coin = 0.0;

    public Coin() {
    }

    public Coin(double coin) {
        this.coin = coin;
    }

    public double getCoin() {
        return this.coin;
    }

    public void posCoin(double c) {
        coin += c;
    }

    public void navCoin(double c) {
        coin -= c;
    }

}
