
public class Main {
    public static void main(String[] args) {
        // Создаем два счета
        BankAccount account1 = new BankAccount("Анастасия Самбурская");
        BankAccount account2 = new BankAccount("Нурлан Сабуров");

        // Пополняем первый счет
        account1.deposit(1000);
        System.out.println("\t*\t*\t*\tПосле пополнения на 1000:\t*\t*\t*\t");
        System.out.println(account1);
        System.out.println(account2);

        // Переводим деньги со счета 1 на счет 2
        account1.transfer(account2, 400);
        System.out.println("\n\t*\t*\t*\tПосле перевода 400:\t*\t*\t*\t");
        System.out.println(account1);
        System.out.println(account2);

        // Пытаемся снять больше, чем есть на счете
        boolean success = account1.withdraw(700);
        System.out.println("\n\t*\t*\t*\tПопытка снять 700: " + (success ? "Успешно" : "Неудачно") +"\t*\t*\t*\t");
        System.out.println(account1);

        // Проверка equals
        System.out.println("\nСчета одинаковые? " + (account1.equals(account2) ? "Да" : "Нет"));

        System.out.println("\nхэш код счета 1 " + account1.hashCode());
        System.out.println("\nхэш код счета 2 " + account2.hashCode());
    }
}