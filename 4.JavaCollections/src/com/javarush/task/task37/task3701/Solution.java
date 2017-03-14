package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return Solution.this.size() != 0;
        }

        @Override
        public T next() {
            checkForComodification();
            int i = cursor;
            if (i >= Solution.this.size())
                throw new NoSuchElementException();
            if (i >= Solution.this.size())
                throw new ConcurrentModificationException();
            cursor = i + 1;
            if (cursor >= Solution.this.size()) cursor = 0;
            return (T) Solution.this.get(lastRet = i);
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                Solution.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
