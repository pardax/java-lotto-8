package lotto;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        findDuplicate(numbers);
        chkConditions(numbers);
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

    private void chkConditions(List<Integer> numbers){
        for(int a : numbers){
            if(a < 1 || a > 45)
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public List<Integer> getLotto(){
        return this.numbers;
    }
}
