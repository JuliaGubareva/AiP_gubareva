import java.util.Arrays;
import java.util.Random;

public class SortingComparison {
    // 1. сортировка вставками !!!! 
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; 
            int j = i - 1; // Начинаем сравнивать с предыдущего элемента
            while (j >= 0 && arr[j] > key) {     //элементы, которые больше key, двигаем вправо
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // вставляем key на правильное место
        }
    }

    // 2. сортировка расческой !!!!
    public static void combSort(int[] arr) {
        int n = arr.length;
        int gap = n; 
        boolean swapped = true; // "флаг для отслеживания обменов", т.е. проверка
        while (gap != 1 || swapped) { 
            gap = (int) (gap / 1.3);  
            if (gap < 1) {
                gap = 1; 
            }
            swapped = false; // сбрасываем флаг обменов
            for (int i = 0; i < n - gap; i++) { 
                if (arr[i] > arr[i + gap]) { 
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    swapped = true; // Отмечаем, что был обмен
                }
            }
        }
    }

    // 3. Пирамидальная сортировка !!!!!! 
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i); 
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    //вспомогательная функция для пирамид. сорт.
    private static void heapify(int[] arr, int n, int i) {
        int largest = i; 
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        } 
    }

    //Генерация случайного массива
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    //Генерация частично отсортированного массива (75% элементов на своих местах)
    public static int[] generatePartiallySortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        Random random = new Random();
        //Перемешиваем 25% элементов
        for (int i = 0; i < size / 4; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
        return arr;
    }

    //Генерация обратно отсортированного массива
    public static int[] generateReverseSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    //Генерация массива с множеством дубликатов (10% уникальных значений)
    public static int[] generateArrayWithDuplicates(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size / 10); //10% уникальных значений
        }
        return arr;
    }

    //Генерация почти отсортированного массива (90% элементов на своих местах)
    public static int[] generateAlmostSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        Random random = new Random();
        //перемешиваем 10% элементов
        for (int i = 0; i < size / 10; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
        return arr;
    }

    //Замер времени выполнения сортировки
    public static long measureTime(Runnable sortFunction, int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length); //копируем массив, чтобы не испортить ориг
        long startTime = System.nanoTime();
        sortFunction.run(); 
        long endTime = System.nanoTime(); 
        return endTime - startTime; //время выполнения в наносекундах
    }

    public static void main(String[] args) {
        //Размеры массивов для тестирования
        int[] smallSize = {100}; //если надо будет больше примеров, просто {100, 500} например
        int[] mediumSize = {1000};
        int[] largeSize = {10000};
        int[][] sizes = {smallSize, mediumSize, largeSize};
        String[] sizeNames = {"Малый", "Средний", "Большой"};

        //типы тест. массивов
        String[] arrayTypes = {
            "Случайный",
            "Частично отсортированный (75%)",
            "Обратно отсортированный",
            "С дубликатами (10% уникальных)",
            "Почти отсортированный (90%)"
        };

        //Запускаем тесты для каждого размера и типа массива
        for (int k = 0; k < sizes.length; k++) {
            System.out.println("Размер массива: " + sizeNames[k]);
            for (int size : sizes[k]) {
                System.out.println("  Размер: " + size);
                for (int i = 0; i < arrayTypes.length; i++) {
                    System.out.println("    Тип массива: " + arrayTypes[i]);
                    int[] arr = null;
                    //генерим массив в зависимости от типа
                    switch (i) {
                        case 0: arr = generateRandomArray(size); break;
                        case 1: arr = generatePartiallySortedArray(size); break;
                        case 2: arr = generateReverseSortedArray(size); break;
                        case 3: arr = generateArrayWithDuplicates(size); break;
                        case 4: arr = generateAlmostSortedArray(size); break;
                    }

                    // Тест !!!!!!
                    for (int j = 0; j < 3; j++) {
                        long totalTime = 0;
                        for (int run = 0; run < 5; run++) { //запускаем сортировку 5 раз и замеряем время
                            int[] arrCopy = Arrays.copyOf(arr, arr.length);
                            long time;
                            switch (j) {
                                case 0:
                                    time = System.nanoTime();
                                    insertionSort(arrCopy);
                                    time = System.nanoTime() - time;
                                    break;
                                case 1:
                                    time = System.nanoTime();
                                    combSort(arrCopy);
                                    time = System.nanoTime() - time;
                                    break;
                                case 2:
                                    time = System.nanoTime();
                                    heapSort(arrCopy);
                                    time = System.nanoTime() - time;
                                    break;
                                default:
                                    time = 0;
                            }
                            totalTime += time;
                        }
                        long avgTime = totalTime / 5; //ср время
                        String algorithmName = "";
                        switch (j) {
                            case 0: algorithmName = "Сортировка вставками"; break;
                            case 1: algorithmName = "Сортировка расчёской"; break;
                            case 2: algorithmName = "Пирамидальная сортировка"; break;
                        }
                        System.out.printf("      %s: %.3f мс%n", algorithmName, avgTime / 1_000_000.0);
                    }
                }
            }
        }
    }
}
