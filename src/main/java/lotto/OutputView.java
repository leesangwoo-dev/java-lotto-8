package lotto;

import java.util.List;

public class OutputView {

    private static final String PURCHASE_COUNT_MESSAGE_FORMAT = "%d개를 구매했습니다.";

    // 구매한 로또 개수와 번호를 출력
    public void printLottos(int count, List<Lotto> lottos) {
        System.out.printf((PURCHASE_COUNT_MESSAGE_FORMAT) + "%n", count);
        printLottoNumbers(lottos);
    }

    // 로또 번호 목록을 출력
    private void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }
}
