package wy.disruptor;

/**
 * @author wangying
 * @description: TODO
 * @date 2021/4/9 10:45
 */
public final class FalseSharing2 implements Runnable {
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private static VolatileLong volatileLong;
    private String groupId;

    public FalseSharing2(String groupId) {
        this.groupId = groupId;
    }

    public static void main(final String[] args) throws Exception {
        // Thread.sleep(10000);
        System.out.println("starting....");

        volatileLong = new VolatileLong();
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration = " + (System.currentTimeMillis() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread t0 = new Thread(new FalseSharing2("t0"));
        Thread t1 = new Thread(new FalseSharing2("t1"));
        t0.start();
        t1.start();
        t0.join();
        t1.join();
    }

    public void run() {
        long i = ITERATIONS + 1;
        if (groupId.equals("t0")) {
            while (0 != --i) {
                volatileLong.value1 = i;
                volatileLong.value2 = i;
            }
        } else if (groupId.equals("t1")) {
            while (0 != --i) {
                volatileLong.value3 = i;
                volatileLong.value4 = i;
            }
        }
    }
}
