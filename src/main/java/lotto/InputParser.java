package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {

    private static final String DELIMITER = ",";

    // 당첨 번호 문자열을 Integer 리스트로 변환
    public static List<Integer> parseWinningNumbers(String input) {
        String[] numberStrings = input.split(DELIMITER);

        return Arrays.stream(numberStrings)
                .map(String::trim)
                .map(InputParser::parseNumber)
                .collect(Collectors.toList());
    }

    // 단일 번호 문자열을 Integer로 변환
    public static int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 번호는 숫자여야 합니다.");
        }
    }

    // 번호 파싱 중 NumberFormatException 발생 시 IllegalArgumentException으로 변환
    private static int parseNumber(String numberString) {
        try {
            return Integer.parseInt(numberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자만 입력 가능합니다.");
        }
    }
}
