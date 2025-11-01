package lotto;

public enum Rank {

    FIRST(6, false, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD(5, false, 1_500_000, "5개 일치 (1,500,000원)"),
    FOURTH(4, false, 50_000, "4개 일치 (50,000원)"),
    FIFTH(3, false, 5_000, "3개 일치 (5,000원)"),
    MISS(0, false, 0, "낙첨");

    private final int matchCount;
    private final boolean matchBonus;
    private final int prizeMoney;
    private final String message;

    Rank(int matchCount, boolean matchBonus, int prizeMoney, String message) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prizeMoney = prizeMoney;
        this.message = message;
    }

    // 일치 개수와 보너스 번호 일치 여부를 받아 Rank를 반환합니다.
    public static Rank valueOf(int matchCount, boolean matchBonus) {
        // 3개 미만 일치 시 MISS 반환
        if (matchCount < 3) {
            return MISS;
        }

        // 6개 일치 (1등)
        if (matchCount == 6) {
            return FIRST;
        }

        // 5개 일치
        if (matchCount == 5) {
            return findRankForFiveMatches(matchBonus);
        }

        // 4개 일치 (4등)
        if (matchCount == 4) {
            return FOURTH;
        }

        // 3개 일치 (5등)
        return FIFTH;
    }

    // 5개 일치 시 보너스 번호 일치 여부에 따라 2등 또는 3등 결정
    private static Rank findRankForFiveMatches(boolean matchBonus) {
        if (matchBonus) {
            return SECOND;
        }
        return THIRD;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getMessage() {
        return message;
    }
}
