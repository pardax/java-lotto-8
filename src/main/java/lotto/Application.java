package lotto;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        LottoMachine lotMachine = new LottoMachine();

        //금액 입력
        lotMachine.InputMoney();

        //당첨번호 입력
        while(true){
            System.out.println("당첨 번호를 입력해 주세요.");
            break;
        }

        while(true){
            System.out.println("보너스 번호를 입력해 주세요.");
            break;
        }
        //보너스 입력
    }
}
