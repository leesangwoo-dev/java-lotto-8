package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        // 요구사항: 로또 번호는 오름차순으로 정렬
        Collections.sort(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // 로또 번호 범위 (1~45) 검증
    private void validateRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> number < 1 || number > 45)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    // 로또 번호 중복 검증 (else 예약어 사용 금지 규칙 준수)
    private void validateDuplicate(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    // 요구사항: 발행한 로또 번호 출력 형식 맞춤
    @Override
    public String toString() {
        String numbersString = numbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        return "[" + numbersString + "]";
    }
}
