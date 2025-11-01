package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.util.ExceptionMessage;

public class InputView {
    private static final String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final int LOTTO_PRICE = 1000;

    public long readPurchaseAmount() {
        try {
            System.out.println(INPUT_PURCHASE_AMOUNT);
            String input = Console.readLine();
            return validateAmount(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readPurchaseAmount();
        }
    }

    private long validateAmount(String input) {
        long amount = parseAmount(input);
        validateAmountUnit(amount);
        return amount;
    }

    private long parseAmount(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_NOT_NUMBER.getMessage());
        }
    }

    private void validateAmountUnit(long amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_UNIT.getMessage());
        }
    }

    public Lotto readWinningLotto() {
        try {
            System.out.println(INPUT_WINNING_NUMBERS);
            String input = Console.readLine();
            List<Integer> numbers = parseNumbers(input);
            return new Lotto(numbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readWinningLotto();
        }
    }

    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    public int readBonusNumber() {
        try {
            System.out.println(INPUT_BONUS_NUMBER);
            String input = Console.readLine();
            return parseBonusNumber(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readBonusNumber();
        }
    }

    private int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.BONUS_NUMBER_RANGE.getMessage());
        }
    }
}
