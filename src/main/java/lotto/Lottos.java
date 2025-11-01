package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(int count, LottoGenerator generator) {
        this.lottos = generateLottos(count, generator);
    }

    // 구매 개수만큼 로또를 생성
    private List<Lotto> generateLottos(int count, LottoGenerator generator) {
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            generatedLottos.add(generator.generateLotto());
        }
        return generatedLottos;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
