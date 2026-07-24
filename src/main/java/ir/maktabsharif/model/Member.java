package ir.maktabsharif.model;

import ir.maktabsharif.exception.InvalidDataException;

import java.util.Objects;

public class Member extends BaseModel<Long> {
    private String fullName;
    private String number;

    public Member() {
    }

    public Member(String fullName, String number) {
        setFullName(fullName);
        setNumber(number);
    }

    public Member(Long id, String fullName, String number) {
        super(id);
        setFullName(fullName);
        setNumber(number);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()){
            throw new InvalidDataException("Name can not be null or empty!");
        }
        this.fullName = fullName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number == null || number.isBlank()){
            throw new InvalidDataException("Number can not be null or empty!");
        }
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(fullName, member.fullName) && Objects.equals(number, member.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, number);
    }

    @Override
    public String toString() {
        return String.format("""
                Member
                ID: %d
                Full Name: %s
                Number: %s
                """, getId(), getFullName(), getNumber());
    }
}
