package com.xym.myJava.jdk8.stream;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 字符统计
 *
 * @author xym
 * @create 2019-07-11 22:15
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    private final String src;
    private int currentChar = 0;

    public WordCounterSpliterator(String src) {
        this.src = src;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(src.charAt(currentChar++));
        //如果还有字符要处理，则返回true
        return currentChar < src.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        //20-0
        int currentSize = src.length() - currentChar;
        if (currentSize < 10) {
            //返回null表示要解析的String已经足够小，可以顺序处理
            return null;
        }
        //hello world aaa bbb
        //splitPos=10+0
        for (int splitPos = currentSize / 2 + currentChar; splitPos < src.length(); splitPos++) {
            if (Character.isWhitespace(src.charAt(splitPos))) {
                //创 建 一 个 新WordCounterSpliterator来解析src从开始到拆分位置的部分
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(src.substring(currentChar,
                                splitPos));
                //将这个 WordCounterSpliterator 的 起 始位置设为拆分位置
                System.out.println("currentChar=" + currentChar + ",splitPos=" + splitPos);
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return src.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

    private int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static void main(String[] args) {
        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";

        WordCounterSpliterator spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        //System.out.println("Found " + spliterator.countWords(stream) + " words");
        //换做并行流就会出现问题，是因为分割位置不对
        System.out.println("Found " + spliterator.countWords(stream.parallel()) + " words");

        //创建自定义分割器后，发现并行可以正常工作了
        Stream<Character> stream2 = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + spliterator.countWords(stream2) + " words");
    }

    class WordCounter {
        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastSpace ?
                        this :
                        new WordCounter(counter, true);
            } else {
                return lastSpace ?
                        new WordCounter(counter + 1, false) :
                        this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(counter + wordCounter.counter,
                    wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }
}
