package part2;

public class BetPosition {

    private double payout;
    private double betAmount;
    private int horseId;

    public BetPosition(double d, int betAmount, int horseId) {
        this.payout = d;
        this.betAmount = betAmount;
        this.horseId = horseId;
    }

    @Override
    public String toString() {
        return "BetPosition(" +
                "payout=" + payout +
                ", betAmount=" + betAmount +
                ", horseId='" + horseId + '\'' +
                ')';
    }

    public int getReturn(boolean win) {
        if (win) {
            return (int) (((double) payout) * betAmount);
        } else {
            int Val = (int) (-1 * betAmount);
            return Val;
        }
    }

    public int getHorseId() {
        return horseId;
    }
}
