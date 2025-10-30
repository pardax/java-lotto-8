package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class LottoMachine
{
    private int money = -1;
    private int count = -1;
    private int bonusNum = -1;
    private Lotto goalLotto;

    private List<Lotto> hasLotto;

    public LottoMachine(){
        hasLotto = new ArrayList<>();
    }

    public void buyLotto(){
        for(int i = 0; i < count; i++){
            List<Integer> temp = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto lot = new Lotto(temp);
            hasLotto.add(lot);
        }
    }

    public void printMyLottos(){
        for(Lotto a : hasLotto){
            System.out.println(a.getLotto());
        }
    }

    public void printResult(){
        int result, first, second, third, fourth, fifth;
        result = first = second = third = fourth = fifth = 0;

        //5등수집
        for(Lotto a : hasLotto){
            if(findLottoMatch(a) == LottoPrize.FIFTH){
                fifth++;
                result += LottoPrize.FIFTH.getPrize();
            }
        }

        //4등수집
        for(Lotto a : hasLotto){
            if(findLottoMatch(a) == LottoPrize.FOURTH){
                fourth++;
                result += LottoPrize.FOURTH.getPrize();
            }
        }

        //3등수집
        for(Lotto a : hasLotto){
            if(findLottoMatch(a) == LottoPrize.THIRD){
                third++;
                result += LottoPrize.THIRD.getPrize();
            }
        }

        //2등수집
        for(Lotto a : hasLotto){
            if(findLottoMatch(a) == LottoPrize.SECOND){
                second++;
                result += LottoPrize.SECOND.getPrize();
            }
        }

        //1등수집
        for(Lotto a : hasLotto){
            if(findLottoMatch(a) == LottoPrize.FIRST){
                first++;
                result += LottoPrize.FIRST.getPrize();
            }
        }

        String s;

        System.out.println("당첨 통계\n---");

        //5등 포맷
        s = String.format("%d개 일치 (%,d원) - %d개", LottoPrize.FIFTH.getCount(), LottoPrize.FIFTH.getPrize(), fifth);
        System.out.println(s);

        //4등 포맷
        s = String.format("%d개 일치 (%,d원) - %d개", LottoPrize.FOURTH.getCount(), LottoPrize.FOURTH.getPrize(), fourth);
        System.out.println(s);

        //3등 포맷
        s = String.format("%d개 일치 (%,d원) - %d개", LottoPrize.THIRD.getCount(), LottoPrize.THIRD.getPrize(), third);
        System.out.println(s);

        //2등 포맷
        s = String.format("%d개 일치, 보너스 볼 일치 (%,d원) - %d개", LottoPrize.SECOND.getCount(), LottoPrize.SECOND.getPrize(), second);
        System.out.println(s);

        //1등 포맷
        s = String.format("%d개 일치 (%,d원) - %d개", LottoPrize.FIRST.getCount(), LottoPrize.FIRST.getPrize(), first);
        System.out.println(s);

        float ret = (float)result/money * 100;
        s = String.format("총 수익률은 %.1f%%입니다.", ret);
        System.out.println(s);
    }

    public LottoPrize findLottoMatch(Lotto lot){
        List<Integer> a = lot.getLotto();
        List<Integer> b = goalLotto.getLotto();
        List<Integer> temp = new ArrayList<>(a);

        temp.retainAll(b);

        return LottoPrize.getValue(temp.size(), a.contains(bonusNum));
    }

    public void inputBounsNum(){
        boolean tsk = true;
        int num = -1;
        List<Integer> temp = goalLotto.getLotto();

        while(tsk){
            String str = getInput("보너스 번호를 입력해 주세요.");

            //파싱 검사
            try{
                num = convertNum(str);
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }

            //무결성 검사
            try{
                tsk = chkNumCondition(num);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

            if(tsk == false)
                this.bonusNum = num;
        }
    }

    private boolean chkNumCondition(int num){
        if(num < 1 || num > 45)
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");

        for(int a : goalLotto.getLotto()){
            if(a == num)
                throw new IllegalArgumentException("[ERROR] 중복된 번호는 입력 할 수 없습니다.");
        }

        return false;
    }


    public void inputGoalNum(){
        boolean tsk = true;

        while(tsk){
            String str = getInput("당첨 번호를 입력해 주세요.");
            String[] sp = str.split(",");
            List<Integer> temp = new ArrayList<>();

            try{
                convertNum(temp, sp);
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }

            try{
                goalLotto = new Lotto(temp);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

            if(goalLotto != null && goalLotto.getLotto().size() == 6)
                tsk = false;
        }
    }

    private int convertNum(String s){
        try{
            int res = Integer.parseInt(s);
            return res;
        }catch (NumberFormatException e){
            throw new NumberFormatException("[ERROR] 입력에 정수 외 문자가 포함되어 있습니다.");
        }
    }

    private List<Integer> convertNum(List<Integer> lst, String[] sp){
        for(String a : sp){
            try{
                lst.add(Integer.parseInt(a));
            }catch (NumberFormatException e){
                throw new NumberFormatException("[ERROR] 입력에 정수 외 문자가 포함되어 있습니다.");
            }
        }
        return lst;
    }

    private String getInput(String msg){
        System.out.println(msg);
        String s = Console.readLine();
        return s;
    }

    public void inputMoney(){
        boolean tsk = true;

        while(tsk){
            String str = getInput("구입금액을 입력해 주세요.");
            int m = -1;

            try{
                m = Integer.parseInt(str);
            }catch (NumberFormatException e) {
                System.out.println("[ERROR] 입력에 정수 외 문자가 포함되어 있습니다.");
                continue;
            }

            try{
                tsk = convertMoney(m);
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean convertMoney(int money){
        int res = money / 1000;
        if(res < 1) throw new IllegalArgumentException("[ERROR] 1000이상의 값을 입력해주세요");

        this.money = money;
        this.count = res;

        String s = String.format("%d개를 구매했습니다.", count);
        System.out.println(s);
        return false;
    }

    public int getCount(){
        return this.count;
    }

    public Lotto getGoalLotto() {
        return goalLotto;
    }

    public int getBonusNum() {
        return bonusNum;
    }

    //테스트용
    public void setGoalLotto(Lotto lot){
        this.goalLotto = lot;
    }

    //테스트용
    public void setBonusNum(int num){
        this.bonusNum = num;
    }

    public void run(){
        inputMoney();
        buyLotto();
        printMyLottos();
        inputGoalNum();
        inputBounsNum();
        printResult();
    }

}
