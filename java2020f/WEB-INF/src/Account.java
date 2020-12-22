/* 316025 Yuta Uki */
/* Account.java */

public class Account {

    private String name;
    private int balance;

    public Account(String myName) {
        name = myName;
        balance = 0;
    } 

    public int deposit(int amount) {
        if(amount > 0) {
            balance += amount;
            return 0;
        }
        else return -3;
    }

    public int withdraw(int amount) {
        //正常
        if(amount > 0)
        {
            if(amount <= balance)
            {
                balance -= amount;
                return 0;
            }
            else return -1; // 残高不足
        }
        else return -3; // マイナス引き出し
    }

    public int showBalance() 
    {
        return balance;
    } 
}
