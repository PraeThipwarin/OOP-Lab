class NumberPrinter implements Runnable {
    private int threadNumber;

    public NumberPrinter(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("thread #" + threadNumber + ": " + i);
        }
    }
}

