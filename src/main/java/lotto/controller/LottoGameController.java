package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoGameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoService = new LottoService();
    }

    public void run() {
        long purchaseAmount = inputView.readPurchaseAmount();
        List<Lotto> purchasedLottos = purchaseLottos(purchaseAmount);
        WinningLotto winningLotto = readWinningLotto(purchasedLottos);

        Map<LottoRank, Integer> statistics = lottoService.calculateStatistics(purchasedLottos, winningLotto);
        double roi = lottoService.calculateRateOfReturn(purchaseAmount, statistics);

        outputView.printStatistics(statistics, roi);
    }

    private List<Lotto> purchaseLottos(long purchaseAmount) {
        int lottoCount = lottoService.calculateLottoCount(purchaseAmount);
        List<Lotto> purchasedLottos = lottoService.generateLottos(lottoCount);
        outputView.printLottos(purchasedLottos);
        return purchasedLottos;
    }

    private WinningLotto readWinningLotto(List<Lotto> purchasedLottos) {
        Lotto winningNumbers = inputView.readWinningLotto();
        return readBonusNumber(winningNumbers);
    }

    private WinningLotto readBonusNumber(Lotto winningNumbers) {
        try {
            int bonusNumber = inputView.readBonusNumber();
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readBonusNumber(winningNumbers);
        }
    }
}
