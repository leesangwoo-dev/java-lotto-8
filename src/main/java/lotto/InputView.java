package lotto;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";

    // 구입 금액을 입력받아 문자열로 반환
    public String readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
        return Console.readLine();
    }

    // 에러 메시지 출력
    public void printError(String message) {
        System.out.println(message);
    }
}
