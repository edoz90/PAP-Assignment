package pap.ass04.textball;

import java.util.stream.LongStream;

public class CruncherThread extends Thread {

    private Long start;
    private Long end;
    private Secret secret;

    public CruncherThread(String name, Long start, Long end, Secret s) {
        super(name);
        this.start = start;
        this.end = end;
        this.secret = s;
    }

    @Override
    public void run() {
        LongStream.rangeClosed(this.start, this.end).forEach(i -> {
            if (this.secret.guess(i)) {
                throw new RuntimeException(i + "");
            }
        });
    }
}
