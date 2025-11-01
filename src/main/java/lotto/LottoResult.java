package lotto;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoResult {

    private final Map<Rank, Integer> statistics;

    // 생성자에서 당첨 통계 계산
    public LottoResult(Lottos purchasedLottos, WinningLotto winningLotto) {
        this.statistics = calculateStatistics(purchasedLottos.getLottos(), winningLotto);
    }

    // 구매한 로또들을 순회하며 각 로또의 당첨 등수를 계산하고 통계 맵에 기록
    private Map<Rank, Integer> calculateStatistics(List<Lotto> purchasedLottos, WinningLotto winningLotto) {
        Map<Rank, Integer> stats = initializeStatistics();

        purchasedLottos.forEach(lotto -> {
            Rank rank = getRankOfLotto(lotto, winningLotto);
            if (rank != Rank.MISS) {
                stats.put(rank, stats.getOrDefault(rank, 0) + 1);
            }
        });

        return stats;
    }

    // 통계 맵을 Rank Enum의 모든 값으로 초기화 (개수 0)
    private Map<Rank, Integer> initializeStatistics() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .collect(Collectors.toMap(
                        Function.identity(),
                        rank -> 0,
                        (a, b) -> b,
                        () -> new EnumMap<>(Rank.class)
                ));
    }

    // 단일 로또의 당첨 등수를 결정
    private Rank getRankOfLotto(Lotto lotto, WinningLotto winningLotto) {
        int matchCount = countMatchingNumbers(lotto, winningLotto.getLotto());
        boolean matchBonus = lotto.getNumbers().contains(winningLotto.getBonusNumber());

        return Rank.valueOf(matchCount, matchBonus);
    }

    // 당첨 번호와의 일치 개수 계산
    private int countMatchingNumbers(Lotto purchasedLotto, Lotto winningLotto) {
        return (int) purchasedLotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();
    }

    // 총 당첨금 계산
    private long calculateTotalPrize() {
        return statistics.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    // 수익률 계산 (소수점 둘째 자리에서 반올림)
    public double calculateYield(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        double yield = (double) totalPrize / purchaseAmount * 100;

        // 소수점 둘째 자리에서 반올림
        return Math.round(yield * 10.0) / 10.0;
    }

    // 외부에서 통계를 읽기 전용으로 접근
    public Map<Rank, Integer> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
