package org.example;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Кастомная реализация ArrayList. Также является не потокобезопасной!
 * Поддерживает операции: добавление, добавление по индексу (со смещением), получение или
 * удаление по индексу, очистка коллекции, сортировка коллекции, замена по индексу
 *
 * @param <T> задает тип элементов, хранимых в списке
 */

public class ArrayList<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_SIZE = 32;

    /**
     * Создает список начального размера. Начальный размер определяется значением {@link #DEFAULT_SIZE}
     */
    public ArrayList(){
        elements = new Object[DEFAULT_SIZE];
    }

    private void checkSize(){
        if(size == elements.length){
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private void checkIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Ожидалось значение от 0 до " + (size - 1));
        }
    }

    private  void checkIndexAdd(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Ожидалось значение от 0 до " + size);
        }
    }

    //region quicksort
    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = (T) elements[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare((T) elements[j], pivot, comparator) <= 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private int compare(T e1, T e2, Comparator<? super T> comparator) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        } else if (e1 instanceof Comparable) {
            return ((Comparable<? super T>) e1).compareTo(e2);
        } else {
            throw new ClassCastException("Elements must be either Comparable or a Comparator must be provided.");
        }
    }

    private void swap(int i, int j) {
        T temp = (T) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
    //endregion

    /**
     * Добавляет элемент в конец списка
     * @param element добавляемый элемент
     */
    public void add(T element){
        checkSize();
        elements[size++] = element;
    }

    /**
     * Добавляет элемент в список по указанному индексу без замены, смещая элементы начиная с выбраного индекса на одну позицию вправо
     * @param element добавляемый элемент
     * @param index индекс размещения добавляемого элемента
     * @throws IndexOutOfBoundsException если индекс выходит за пределы текущего размера списка
     */
    public void add(T element, int index){
        checkIndexAdd(index);
        checkSize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент списка по указанному индексу
     * @param index индекс требуемого элемента
     * @return элемент находящийся по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы текущего размера списка
     */
    public T get(int index){
        checkIndex(index);
        return (T)elements[index];
    }

    /**
     * Удаляет элемент по указанному индексу
     * @param index индекс удаляемого элемента
     * @throws IndexOutOfBoundsException если индекс выходит за пределы текущего размера списка
     */
    public void remove(int index){
        checkIndex(index);
        int offset = (size - 1) - index;

        if(offset > 0){
            System.arraycopy(elements, index + 1, elements, index, offset);
        }

        elements[--size] = null;
    }

    /**
     * Очищает список, заменяя все элементы значением null
     */
    public void clear(){
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /**
     * Заменяет элемент по указанному индексу новым элементом
     * @param index индекс элемента, который нужно заменить
     * @param element новый элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы текущего размера списка
     */
    public void set(int index, T element){
        checkIndex(index);
        elements[index] = element;
    }

    /**
     * Сортирует список алгоритмом быстрой сортировки (QuickSort).
     * В случае, если компаратор не передан, предполагается, что элементы реализуют Comparable
     * @param comparator компаратор для сортировки, может быть null
     * @throws ClassCastException если элементы не реализуют Comparable и не передан компаратор
     */
    public void sort(Comparator<? super T> comparator){
        quickSort(0, size - 1, comparator);
    }

    /**
     * Возвращает список в виде строки со значениями элементов
     * @return строка, представляющая список
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Возвращает количсество элементов в списке
     *
     * @return текущий размер списка
     */
    public int size() {
        return size;
    }
}

