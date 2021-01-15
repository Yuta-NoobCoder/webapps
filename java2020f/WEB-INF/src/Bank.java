/* 316025 Yuta Uki */
/* Bank.java */

import java.util.Hashtable;

public class Bank {

    private Hashtable<String, Account> customer; /* 口座リスト */

    public Bank() {  
        customer = new Hashtable<String,Account>();
    } 
    public int open(String name) {

        Account account = customer.get(name);

        //同名の口座 が存在しない
        if(account == null) {
            customer.put(name, new Account(name));
            return 0;
        } 
        else return -7;
    }
    public int close(String name) {

        Account account = customer.get(name);

        //指定された口座が存在する
        if(account != null) {
            //かつ残高が0
            if(account.showBalance() == 0) {
                customer.remove(name);
                return 0;
            }
            else return -1;
        }
        else return -7;
    }

    public int deposit(String name, int amount ) { 
        
        Account account = customer.get(name);
        
        //指定された口座が存在する
        if(account != null) {
            //かつ預金額が0以上
            if(account.deposit(amount) == 0) 
                return 0;
            else 
                return -3;
        }
        else return -7;
    }

    public int deposit(String name, String amount) { 
        
        int int_amount, result;
        
        //数値にキャスト
        try {
            int_amount = Integer.parseInt(amount);
        }
        catch(NumberFormatException e) {
            //showBalanceを用いて口座有無の判定
            if(this.showBalance(name) == -7)
                return -7; 
            else
                return -4;
        }

        result = this.deposit(name, int_amount);
        
        return result;
    }

    public int withdraw(String name, int amount) { 

        Account account = customer.get(name);
        int result;
        
        //指定された口座が存在する
        if(account != null) {
            result = account.withdraw(amount);
            //引出額が0以下
            if(result == -3)
                return -3;
            //預金残高を超える 
            else if(result == -1)
                return -1;
            //正常終了
            else return 0;
        }
        else return -7;
    }

    public int withdraw(String name, String amount) { 
        
        int int_amount, result;
        
        //数値にキャスト
        try {
            int_amount = Integer.parseInt(amount);
        }
        catch(NumberFormatException e) {
            //showBalanceを用いて口座有無の判定
            if(this.showBalance(name) == -7)
                return -7; 
            else
                return -4;
        }
        
        result = this.withdraw(name, int_amount);
        
        return result;
    }

    public int showBalance(String name ) {

        Account account = customer.get(name);
        
        //指定された口座が存在する
        if(account != null) {
            return account.showBalance();
        }
        else return -7;
    }
}