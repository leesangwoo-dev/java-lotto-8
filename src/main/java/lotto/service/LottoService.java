package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private static final int ROUND_UP_FACTOR = 10;

    public int calculateLottoCount(long amount) {
        return (int) (amount / LOTTO_PRICE);
    }

    public List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateSingleLotto());
        }
        return lottos;
    }

    private Lotto generateSingleLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_SIZE);
        return new Lotto(numbers);
    }

    public Map<LottoRank, Integer> calculateStatistics(List<Lotto> purchasedLottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> statistics = initializeStatistics();
        for (Lotto purchasedLotto : purchasedLottos) {
            LottoRank rank = purchasedLotto.match(winningLotto.getWinningLotto(), winningLotto.getBonusNumber());
            updateStatistics(statistics, rank);
        }
        return statistics;
    }

    private Map<LottoRank, Integer> initializeStatistics() {
        Map<LottoRank, Integer> statistics = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            statistics.put(rank, 0);
        }
        return statistics;
    }

    private void updateStatistics(Map<LottoRank, Integer> statistics, LottoRank rank) {
        statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
    }

    public double calculateRateOfReturn(long purchaseAmount, Map<LottoRank, Integer> statistics) {
        long totalPrize = calculateTotalPrize(statistics);
        double roi = (double) totalPrize / purchaseAmount * 100;
        return roundUpRoi(roi);
    }

    private long calculateTotalPrize(Map<LottoRank, Integer> statistics) {
        long totalPrize = 0;
        for (LottoRank rank : LottoRank.values()) {
            totalPrize = totalPrize + rank.getPrize() * statistics.getOrDefault(rank, 0);
        }
        return totalPrize;
    }

    private double roundUpRoi(double roi) {
        return Math.round(roi * ROUND_UP_FACTOR) / (double) ROUND_UP_FACTOR;
    }
}
