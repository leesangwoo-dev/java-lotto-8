package lotto;

public class WinningLotto {

    // 당첨 번호는 Lotto 클래스에서 유효성을 검증합니다.
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    // 보너스 번호에 대한 유효성 검증을 수행합니다.
    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        validateRange(bonusNumber);
        validateDuplication(lotto, bonusNumber);
    }

    // 보너스 번호가 1~45 범위에 속하는지 검증합니다. (else 사용 금지 규칙 준수)
    private void validateRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    // 보너스 번호가 당첨 번호와 중복되는지 검증합니다. (else 사용 금지 규칙 준수)
    private void validateDuplication(Lotto lotto, int bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
