package com.epam.rd.autotasks;

public class DecrementingCarouselWithLimitedRun extends DecrementingCarousel {
    private final int actionLimit;

    public DecrementingCarouselWithLimitedRun(final int capacity, final int actionLimit) {
        super(capacity);
        this.actionLimit = actionLimit;
    }

    @Override
    public CarouselRun run() {
        if (!isRun) {
            isRun = true;
            return new DecrementingCarouselWithLimitRun();
        }
        return null;
    }

    public class DecrementingCarouselWithLimitRun extends CarouselRun {
        private int decrement = 1;
        private int actionsTaken = 0;

        @Override
        public int next() {
            if (isFinished() || actionsTaken >= actionLimit) {
                return -1;
            }

            int beforeDecreasing = array[position];
            array[position] -= decrement;
            actionsTaken++;

            do {
                position++;
            } while (array[position %= array.length] <= 0 && !isFinished());

            return beforeDecreasing;
        }

        @Override
        public boolean isFinished() {
            return super.isFinished() || actionsTaken >= actionLimit;
        }
    }
}
