
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

    public static int counter = 6;

    class MyTimerTask extends TimerTask {

        Timer t;

        MyTimerTask(Timer t) {
            this.t = t;
        }

        @Override
        public void run() {
            counter--;
            if (counter == 0)
                t.cancel();
            TestTimer.this.doStuff();     
        }
    }

    public void doStuff() {
        System.out.println("exec:" + new Date());
    }

    public static void main(String[] args) {

        TestTimer test = new TestTimer();

        Timer timer = new Timer();

        TestTimer.MyTimerTask tt = test.new MyTimerTask( timer );
        timer.schedule(tt, 0, 2000);
        
        System.out.println("GATA");
    }

}
