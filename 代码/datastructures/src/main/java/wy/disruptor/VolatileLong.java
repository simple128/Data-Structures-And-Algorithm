package wy.disruptor;

import com.lmax.disruptor.dsl.Disruptor;
import sun.misc.Contended;

/**
 * @author wangying
 * @description: TODO
 * @date 2021/4/9 10:45
 */
public class VolatileLong {

//    Disruptor disruptor = new Disruptor();

//    public long p1,p2,p3;
//    public volatile long value1 = 0L;
//    public long p5;
//    public volatile long value2 = 0L;
//    public long p7,p8;
//
//    public long p9,p10,p11;
//    public volatile long value3 = 0L;
//    public long p13;
//    public volatile long value4 = 0L;
//    public long p15,p16;

    @Contended("group0")
    public volatile long value1 = 0L;
    @Contended("group0")
    public volatile long value2 = 0L;

    @Contended("group1")
    public volatile long value3 = 0L;
    @Contended("group1")
    public volatile long value4 = 0L;
}
