package lotto;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        findDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
    private void findDuplicate(List<Integer> numbers){
        Set<Integer> hash = new HashSet<>(numbers);
        if(numbers.size() != hash.size()){
            throw new IllegalArgumentException("[ERROR] 중복된 번호는 입력 할 수 없습니다.");
        }
    }

    public List<Integer> GetLotto(){
        return this.numbers;
    }
}
