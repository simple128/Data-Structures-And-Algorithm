package wy.test;

/**
 * @author wangying
 * @description: TODO
 * @date 2021/3/19 17:39
 */
public class AccountingSync implements Runnable{

    String word;

    public AccountingSync(String word) {
        this.word = word;
    }

    @Override
    public void run() {
        System.out.println(word);
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new AccountingSync("A"));
        Thread t2=new Thread(new AccountingSync("B"));
        Thread t3=new Thread(new AccountingSync("C"));
        t1.start();
        t2.start();
        t3.start();
    }

}
