package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BillingDetails {

    private String bankName;
    private String swiftCode;

    public BankAccount() {
    }

    @Column(name = "bank_name", nullable = false)
    public String getBankName() {
        return bankName;
    }

    @Column(name = "swift_code", nullable = false)
    public String getSwiftCode() {
        return swiftCode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
