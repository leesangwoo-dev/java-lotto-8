package lotto.domain;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000L, "6개 일치 (2,000,000,000원)"),
    SECOND(5, true, 30_000_000L, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD(5, false, 1_500_000L, "5개 일치 (1,500,000원)"),
    FOURTH(4, false, 50_000L, "4개 일치 (50,000원)"),
    FIFTH(3, false, 5_000L, "3개 일치 (5,000원)"),
    NONE(0, false, 0L, "");

    private static final int MATCH_COUNT_FOR_BONUS = 5;

    private final int matchCount;
    private final boolean matchBonus;
    private final long prize;
    private final String message;

    LottoRank(int matchCount, boolean matchBonus, long prize, String message) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
        this.message = message;
    }

    public static LottoRank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount < FIFTH.matchCount) {
            return NONE;
        }
        if (matchCount == MATCH_COUNT_FOR_BONUS) {
            return getRankForFiveMatches(matchBonus);
        }
        for (LottoRank rank : values()) {
            if (rank.matchCount == matchCount && !rank.matchBonus) {
                return rank;
            }
        }
        return NONE;
    }

    private static LottoRank getRankForFiveMatches(boolean matchBonus) {
        if (matchBonus) {
            return SECOND;
        }
        return THIRD;
    }

    public long getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
