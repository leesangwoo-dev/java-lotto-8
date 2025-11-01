package lotto;

public class LottoController {

    private final InputView inputView;
    private PurchaseAmount purchaseAmount;

    public LottoController() {
        this.inputView = new InputView();
    }

    public void run() {
        getPurchaseAmountFromUser();
        // 다음 단계: 로또 발행 및 나머지 게임 로직 (추후 구현 예정)
        System.out.println("\n" + purchaseAmount.getLottoCount() + "개를 구매했습니다. (다음 단계 진행)");
    }

    // 사용자에게 유효한 구입 금액을 받을 때까지 반복 (들여쓰기 깊이 2 이하 유지)
    private void getPurchaseAmountFromUser() {
        while (purchaseAmount == null) {
            processPurchaseAmountInput();
        }
    }

    // 실제 입력 및 유효성 검사 로직을 분리하여 getPurchaseAmountFromUser 메서드의 들여쓰기 깊이 감소
    private void processPurchaseAmountInput() {
        try {
            String input = inputView.readPurchaseAmount();
            purchaseAmount = new PurchaseAmount(input);
        } catch (IllegalArgumentException e) {
            // 요구사항: 예외 발생 시 [ERROR] 메시지 출력
            inputView.printError(e.getMessage());
        }
    }

    // 테스트 및 다음 단계를 위한 getter
    public PurchaseAmount getPurchaseAmount() {
        return purchaseAmount;
    }
}
