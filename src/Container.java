/**
 * Обгортка комплекного числа (контейнер)
 * Створена для можливого збільшення кількості рівнів хеш-таблиць
 */
public interface Container {

    String print(int tLevel); //друкування складу
    void insert(ComplexNumber value); //вставка значення

    ComplexNumber get(ComplexNumber v);
    void pickHashedData(ComplexNumberList pushTo); //передати значення стеку, що даний як параметр
}
