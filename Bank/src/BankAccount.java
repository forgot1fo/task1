import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    private String ownerName;
    private int balance;
    private LocalDateTime openingDate;
    private boolean isBlocked;
    private String accountNumber;

    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
        this.openingDate = LocalDateTime.now();
        this.isBlocked = false;
        this.accountNumber = generateAccountNumber();
    }

    // Генерация номера счета
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // Пополнение счета
    public boolean deposit(int amount) {
        if (isBlocked || amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    // Снятие денег
    public boolean withdraw(int amount) {
        if (isBlocked || amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    // Перевод денег на другой счет
    public boolean transfer(BankAccount otherAccount, int amount) {
        if (isBlocked || otherAccount == null || otherAccount.isBlocked ||
                amount <= 0 || amount > balance) {
            return false;
        }

        // Снимаем деньги с текущего счета
        if (this.withdraw(amount)) {
            // Если снятие прошло успешно, пополняем другой счет
            return otherAccount.deposit(amount);
        }
        return false;
    }

    // Геттеры и сеттеры
    public String getOwnerName() {
        return ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    @Override
    public String toString()
    {
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuffer stringBuffer = new StringBuffer();
        String owner = getOwnerName();
        stringBuffer.append("Имя владельца: "+owner+"\n");
        int balance = getBalance();
        stringBuffer.append("Баланс: "+balance+"\n");
        LocalDateTime dt = getOpeningDate();
        stringBuffer.append("Дата открытия: " +getOpeningDate().format(formatter)+ "\n");
        String block = isBlocked() ? "заблокирован" : "открыт";
        stringBuffer.append("Счёт " +block+ "\n");
        String numb = getAccountNumber();
        stringBuffer.append("Номер счета: "+numb + "\n");
        return stringBuffer.toString();
    }


    // Переопределение метода equals
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj instanceof BankAccount)
        {
            BankAccount bank = (BankAccount) obj;
            if (!bank.getAccountNumber().equals(getAccountNumber()))
            {
                return false;
            }
            return true;
        }
        return false;
    }

    // Переопределение метода hashCode
    @Override
    public int hashCode() {
        return accountNumber != null ? accountNumber.hashCode() : 0;
    }
}
