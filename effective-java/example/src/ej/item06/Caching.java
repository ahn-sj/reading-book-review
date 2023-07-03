package ej.item06;

public class Caching {

    private static int[] cachingArray = new int[100_000];

    public static void main(String[] args) {

        // 1. 캐싱을 했을 때
        long beforeTime = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            cachingArray[i] = 1;
        }
        System.out.println("캐싱 했을 때 소요시간(ms) : " + (System.currentTimeMillis() - beforeTime) + "ms");

        // 2. 캐싱을 하지 않았을 때
        beforeTime = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            int[] nonCachingArray = new int[100_000];
            nonCachingArray[i] = 1;
        }
        System.out.println("캐싱 안 했을 때 소요시간(ms) : " + (System.currentTimeMillis() - beforeTime) + "ms");
    }
}
