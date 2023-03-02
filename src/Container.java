
/**
 * Обгортка комплекного числа (контейнер)
 * Створена для можливого збільшення кількості рівнів хеш-таблиць
 */
public interface Container {

    String print(int tLevel); //друкування складу
    void insert(Hashable value); //вставка значення

    Hashable get(Hashable v); //отримати число
    void pickHashedData(ComplexNumberList pushTo); //передати значення стеку, що даний як параметр
}
