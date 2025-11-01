package lotto.util;

public enum ExceptionMessage {
    PURCHASE_AMOUNT_NOT_NUMBER("구입 금액은 숫자여야 합니다."),
    PURCHASE_AMOUNT_UNIT("구입 금액은 1,000원 단위여야 합니다."),
    LOTTO_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    LOTTO_NUMBER_DUPLICATE("로또 번호는 중복될 수 없습니다."),
    BONUS_NUMBER_RANGE("보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
