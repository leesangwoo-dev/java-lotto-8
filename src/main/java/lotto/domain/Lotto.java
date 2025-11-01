package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList; // ArrayList를 사용하기 위해 import 추가
import lotto.util.ExceptionMessage;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateNumbersRange(numbers);
        validateUniqueNumbers(numbers);
        this.numbers = sortNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_COUNT.getMessage());
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateNumberRange(number);
        }
    }

    private void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private void validateUniqueNumbers(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_DUPLICATE.getMessage());
        }
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        // 불변 리스트(Immutable List)일 경우 UnsupportedOperationException이 발생하므로
        // 정렬을 위해 새로운 ArrayList로 복사합니다.
        List<Integer> mutableNumbers = new ArrayList<>(numbers);
        Collections.sort(mutableNumbers);
        return Collections.unmodifiableList(mutableNumbers); // 불변성 유지를 위해 최종적으로 unmodifiableList로 반환
    }

    public LottoRank match(Lotto winningLotto, int bonusNumber) {
        int matchCount = (int) numbers.stream()
                .filter(winningLotto.numbers::contains)
                .count();
        boolean matchBonus = numbers.contains(bonusNumber);

        return LottoRank.valueOf(matchCount, matchBonus);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
