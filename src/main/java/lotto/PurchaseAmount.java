package lotto;

public class PurchaseAmount {
    // 로또 1장의 가격
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public PurchaseAmount(String input) {
        int parsedAmount = parseAmount(input);
        validate(parsedAmount);
        this.amount = parsedAmount;
    }

    // 입력값을 정수로 변환하며 NumberFormatException 발생 시 IllegalArgumentException으로 변환하여 처리
    private int parseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    // 구입 금액에 대한 모든 유효성 검증을 담당
    private void validate(int amount) {
        validatePositive(amount);
        validateUnit(amount);
    }

    // 구입 금액이 양수인지 검증
    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수여야 합니다.");
        }
    }

    // 구입 금액이 1,000원 단위인지 검증 (else 예약어 사용 금지 규칙 준수)
    private void validateUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }

    // 구입 금액으로 구매 가능한 로또 개수를 반환
    public int getLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}
