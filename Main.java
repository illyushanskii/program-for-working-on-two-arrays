import java.util.Scanner;

public class Main {
    //Створення класу Ordarr
    public static class Ordarr {
        private long[] a;
        private int nElems;
        //Конструктор
        public Ordarr(int max) {
            a = new long[max];
            nElems = 0;
        }
        //Метод що повертає розмір масиву
        public int size() {
            return nElems;
        }
        //Завд.5 метод знаходження елементу в масиві двійковим пошуком
        public int find(long searchKey) {
            int lowerBound = 0;
            int upperBound = nElems - 1;
            int curIn;

            while (true) {
                curIn = (lowerBound + upperBound) / 2;
                if (a[curIn] == searchKey) {
                    return curIn; // Елемент знайдено
                } else if (lowerBound > upperBound) {
                    return nElems; // Елемент не знайдено
                } else {
                    if (a[curIn] < searchKey) {
                        lowerBound = curIn + 1; // Пошук у верхній половині
                    } else {
                        upperBound = curIn - 1; // Пошук у нижній половині
                    }
                }
            }
        }
        //Завд.5 метод для впорядкованого додавання нового елементу до масиву двійковим пошуком
        public void insert(long value) {
            int j;

            // Двійковий пошук для визначення позиції вставки
            int lowerBound = 0;
            int upperBound = nElems - 1;
            while (lowerBound <= upperBound) {
                j = (lowerBound + upperBound) / 2;
                if (a[j] == value) {
                    return; // Такий елемент вже існує
                } else if (a[j] < value) {
                    lowerBound = j + 1; // Шукати в верхній половині
                } else {
                    upperBound = j - 1; // Шукати в нижній половині
                }
            }

            // Вставка нового елемента
            for (j = nElems - 1; j >= lowerBound; j--) {
                a[j + 1] = a[j]; // Зсув елементів праворуч
            }
            a[lowerBound] = value; // Вставка нового значення
            nElems++; // Збільшення лічильника елементів
        }

        //Завд.5 Метод для видалення елементу з масиву двійковим пошуком
        public boolean delete(long value) {
            int j = find(value);//Функція знаходження позиції шуканого елемента
            if (j == nElems) {
                return false; // Елемент не знайдено
            } else {
                for (int k = j; k < nElems; k++) {
                    a[k] = a[k + 1]; // Видалення зсувом вліво
                }
                nElems--;
                return true; // Елемент видалено
            }
        }

        //Метод виведення масиву
        public void display() {
            for (int j = 0; j < nElems; j++) {
                System.out.print(a[j] + " ");
            }
        }

        //Завд.6 метод для впорядкованого об'єднання масивів в один
        public void merge(long[] arr1, int size1, long[] arr2, int size2) {
            int allSize = size1 + size2;
            int inxAll = 0;
            int inx1 = 0;
            int inx2 = 0;

            // Створюємо новий масив для злиття
            long[] arrAll = new long[allSize];

            // Поки обидва вихідних масиви не дійшли кінця
            while (inx1 < size1 && inx2 < size2) {
                if (arr1[inx1] < arr2[inx2]) {
                    arrAll[inxAll++] = arr1[inx1++];
                } else {
                    arrAll[inxAll++] = arr2[inx2++];
                }
            }

            // Додаємо залишок першого масиву, якщо є
            while (inx1 < size1) {
                arrAll[inxAll++] = arr1[inx1++];
            }

            // Додаємо залишок другого масиву, якщо є
            while (inx2 < size2) {
                arrAll[inxAll++] = arr2[inx2++];
            }
            //Цикл знаходження та видалення дублікатів
            for(int i = 0;i < allSize - 1; i++){
                if(arrAll[i] == arrAll[i + 1]){
                    for(int j = i; j < allSize - 1;j++){
                        arrAll[j] = arrAll[j + 1];
                    }
                    allSize -= 1;
                }
            }
            // Оновлюємо змінні
            a = arrAll;
            nElems = allSize;
        }
    }
    public static class OrderedApp {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);// Новий об'єкт в Scanner
            int maxSize = 100;
            Ordarr arrAll = new Ordarr(maxSize * 2);
            Ordarr arr1 = new Ordarr(maxSize);
            Ordarr arr2 = new Ordarr(maxSize);
            //Цикл для введеня кількості елементів 1масиву
            int num;
            do {
                System.out.println("Введіть кільк. елементів 1масиву");
                num = scan.nextInt();
            } while (num < 1 || num > maxSize);

            //Цикл заповнення 1масиву
            long value;
            System.out.println("Заповніть 1масив:");
            for (int i = 0; i < num; i++) {
                value = scan.nextLong();
                arr1.insert(value);
            }
            arr1.display();

            //Цикл для введеня кількості елементів 2масиву
            do {
                System.out.println("\nВведіть кільк. елементів 2масиву");
                num = scan.nextInt();
            } while (num < 1 || num > maxSize);

            //Цикл заповнення 2масиву
            System.out.println("Заповніть 2масив:");
            for (int i = 0; i < num; i++) {
                value = scan.nextLong();
                arr2.insert(value);
            }
            arr2.display();

            //Цикл для вибору масиву
            int choice;
            while (true) {
                System.out.println("\nВийти - 0; Дії з 1 масивом - 1; Дії з 2 масивом - 2; Об'єднати масиви - 3");
                choice = scan.nextInt();
                if (choice == 0) {
                    break;
                }
                switch (choice) {
                    case 1:
                        arrComd(scan, arr1);
                        break;
                    case 2:
                        arrComd(scan, arr2);
                        break;
                    case 3:
                        System.out.println("Об'єднанний масив з двох: ");
                        arrAll.merge(arr1.a, arr1.nElems, arr2.a, arr2.nElems);
                        arrAll.display();
                        break;
                    default:
                        System.out.println("Невірна команда!");
                }
            }
        }
        //Метод з командами для масивів
        private static void arrComd(Scanner scan, Ordarr arr) {
            int comd;
            while (true) {
                System.out.println("\nДодати - 0; Видалити - 1; Знайти - 2; Повернутися - 3");
                comd = scan.nextInt();
                if (comd == 3) {
                    break;
                }
                switch (comd) {
                    case 0:
                        System.out.print("Введіть значення: ");
                        long value = scan.nextLong();
                        arr.insert(value);
                        arr.display();
                        break;
                    case 1:
                        System.out.print("Введіть значення: ");
                        value = scan.nextLong();
                        arr.delete(value);
                        arr.display();
                        break;
                    case 2:
                        System.out.print("Введіть значення: ");
                        value = scan.nextLong();
                        System.out.println((arr.find(value) != arr.nElems) ? "Елемент знайдено: " + value : "Елемент не знайдено: " + value);
                        break;
                    default:
                        System.out.println("Невірна команда!");
                }
            }
        }
    }
}