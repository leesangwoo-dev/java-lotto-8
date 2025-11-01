package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class LottoGenerator {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    // 중복되지 않는 6개의 로또 번호를 생성하여 Lotto 객체를 반환
    public Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                MIN_NUMBER,
                MAX_NUMBER,
                LOTTO_NUMBER_COUNT
        );
        // Lotto 클래스에서 유효성 검증(6개 번호)을 수행하므로 별도 검증 로직은 생략
        return new Lotto(numbers);
    }
}
