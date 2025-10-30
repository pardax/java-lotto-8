package lotto;

public enum LottoPrize
{
    NONE(0, false, 0),
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    END(0, false, 0);

    private final boolean bonus;
    private final int count;
    private final int prize;

    /**
     @param count require count
     @param bonus require bonus
     @param prize gap
     */
    LottoPrize(int count, boolean bonus, int prize){
        this.bonus = bonus;
        this.count = count;
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }

    public int getCount() {
        return count;
    }

    public boolean requiresBonus() {
        return bonus;
    }

    public static LottoPrize getValue(int matchCount, boolean bonusMatch) {
        for (LottoPrize val : values()) {
            if (val.count == matchCount && val.bonus == bonusMatch)
                return val;
        }
        return NONE;
    }
}
