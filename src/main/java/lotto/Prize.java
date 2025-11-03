package lotto;

import java.text.DecimalFormat;
import java.util.Arrays;

public enum Prize {
    FIFTH(3, 5_000, false, 0),
    FOURTH(4, 50_000, false, 0),
    THIRD(5, 1_500_000, false, 0),
    SECOND(5, 30_000_000, true, 0),
    FIRST(6, 2_000_000_000, false, 0),
    ;

    private static final String DECIMAL_PATTERN = "###,###";
    private static final String ROUND_PATTERN = "%.1f";

    private final int matchCount;
    private final int reward;
    private final boolean isMatchBonus;
    private int winnerCount;

    Prize(int matchCount, int reward, boolean isMatchBonus, int winnerCount) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.isMatchBonus = isMatchBonus;
        this.winnerCount = winnerCount;
    }

    public static Prize findMatchCount(long matchNumber, boolean bonus) {
        return Arrays.stream(values())
                .filter(e -> e.matchCount == matchNumber && e.isMatchBonus == bonus)
                .findFirst()
                .orElse(null);
    }

    public static void check(long matchNumber, boolean bonus) {
        if (findMatchCount(matchNumber, bonus) != null) {
            findMatchCount(matchNumber, bonus).winnerCount += 1;
        }

    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getTotalReward() {
        return reward * winnerCount;
    }

    private String makeComma(int number) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_PATTERN);
        return decimalFormat.format(number);
    }

    public void printResult() {
        if (this == SECOND) {
            System.out.println(matchCount + "개 일치, 보너스 볼 일치 " + "(" + makeComma(reward) + "원) - " + winnerCount + "개");
            return;
        }
        System.out.println(matchCount + "개 일치 " + "(" + makeComma(reward) + "원) - " + winnerCount + "개");
    }

    public static double totalReward() {
        return Arrays.stream(values()).mapToInt(Prize::getTotalReward).sum();
    }

    public static String getRateOfReturn(int money) {
        return String.format(ROUND_PATTERN, totalReward() * 100 / money);
    }
}
