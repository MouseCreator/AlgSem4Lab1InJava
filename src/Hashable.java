/**
 * Інтерфейс для класів, які необхідно захешувати
 */
public interface Hashable {
    int hash(int a, int b, int p, int m); //захешувати відповідно до параметрів

    int toInteger(); //вивести як ціле число
}
