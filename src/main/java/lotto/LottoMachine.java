package lotto;

import camp.nextstep.edu.missionutils.Console;

public class LottoMachine
{
    private int count = 0;

    public String GetInput(){
        String str = Console.readLine();
        return str;
    }

    public void InputMoney(){
        boolean tsk = true;
        String str;
        int m;

        while(tsk){
            System.out.println("구입금액을 입력해 주세요.");
            str = Console.readLine();
            m = -1;

            try{
                m = Integer.parseInt(str);
            }catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 입력에 정수 외 문자가 포함되어 있습니다.");
                continue;
            }

            try{
                tsk = ConvertMoney(m);
            }catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 1000이상의 값을 입력해주세요");
            }

        }

    }

    private boolean ConvertMoney(int money){
        int res = money / 1000;
        if(res < 1) throw new IllegalArgumentException();

        this.count = res;
        return false;
    }


    public int GetCount(){
        return this.count;
    }

    public void Run()
    {
        //String a = Console.readLine();
    }
}
