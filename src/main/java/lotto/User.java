package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.ArrayList;

public class User {
    static final String INPUT_RANGE="[0-9]+";
    String money;
    String lottoQuantity;
    List<Lotto> lottos;



    User(){
        this.lottos=new ArrayList<Lotto>();
    }

    void inputMoney(){
        this.money= Console.readLine();
        validate();
    }

    private void validate(){
        if(checkComposeOfNumbers()){
            throw new IllegalArgumentException("입력값은 숫자여야만 합니다.");
        }
        if(checkDivisibleByThousand(money.substring(money.length()-3))){
            throw new IllegalArgumentException("구입금액은 1000원 단위이어야 합니다.");
        }
    }
    private boolean checkComposeOfNumbers(){
        return !money.matches(INPUT_RANGE);
    }

    private boolean checkDivisibleByThousand(String lastThreeDigit){
        if(!lastThreeDigit.equals("000")){
            return true;
        }
        return false;
    }

    void setLottoQuantity(){
        this.lottoQuantity=calculateQuantity();
    }

    private String calculateQuantity(){
        return money.substring(0,money.length()-3);
    }

    void printLottoQunantity(){
        System.out.println();
        System.out.println(this.lottoQuantity+"개를 구매했습니다.");
    }

    void buyingLotto(){
        for(int number=1;number<=Integer.parseInt(lottoQuantity);number++) {
            lottos.add(generateLotto());
        }
    }

    private Lotto generateLotto(){
        return new Lotto(Randoms.pickUniqueNumbersInRange(1,45,6));
    }

    void printAllBuyingLotto(){
        for (Lotto lotto: lottos){
            lotto.printLotto();
        }
        System.out.println();
    }
}
