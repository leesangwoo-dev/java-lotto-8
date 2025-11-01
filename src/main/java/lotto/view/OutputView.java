package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;

public class OutputView {
    private static final String PURCHASE_COUNT_MESSAGE = "%d개를 구매했습니다.";
    private static final String WINNING_STATISTICS_HEADER = "\n당첨 통계\n---";
    private static final String RANK_RESULT_MESSAGE = "%s - %d개";
    private static final String TOTAL_RATE_OF_RETURN_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public void printLottos(List<Lotto> lottos) {
        System.out.println(String.format(PURCHASE_COUNT_MESSAGE, lottos.size()));
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatistics(Map<LottoRank, Integer> statistics, double roi) {
        System.out.println(WINNING_STATISTICS_HEADER);
        List<LottoRank> ranks = getSortedRanks();
        for (LottoRank rank : ranks) {
            printRank(rank, statistics.getOrDefault(rank, 0));
        }
        printRateOfReturn(roi);
    }

    private List<LottoRank> getSortedRanks() {
        List<LottoRank> ranks = Arrays.asList(LottoRank.values());
        Collections.sort(ranks, Comparator.comparing(LottoRank::getPrize)
                .thenComparing(LottoRank::ordinal));
        return ranks.stream()
                .filter(rank -> rank.getPrize() > 0)
                .collect(Collectors.toList());
    }

    private void printRank(LottoRank rank, int count) {
        if (rank.getPrize() > 0) {
            System.out.println(String.format(RANK_RESULT_MESSAGE, rank.getMessage(), count));
        }
    }

    private void printRateOfReturn(double roi) {
        System.out.println(String.format(TOTAL_RATE_OF_RETURN_MESSAGE, roi));
    }
}
