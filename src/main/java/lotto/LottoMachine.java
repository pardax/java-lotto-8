package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.*;

public class LottoMachine
{
    private int count = -1;
    private int bonusNum = -1;
    private Lotto goalLotto;

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

        this.count = res;
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

    public void run(){
        inputMoney();
        inputGoalNum();
        inputBounsNum();
    }

}
