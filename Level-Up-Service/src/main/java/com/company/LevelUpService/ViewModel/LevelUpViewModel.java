package com.company.LevelUpService.ViewModel;

import java.time.LocalDate;
import java.util.Objects;

public class LevelUpViewModel {
    private int levelUpId;
    private int customerId;
    private int points;
    private LocalDate memberDate;

    public int getLevelUpId() {
        return levelUpId;
    }

    public void setLevelUpId(int levelUpId) {
        this.levelUpId = levelUpId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDate getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(LocalDate memberDate) {
        this.memberDate = memberDate;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelUpViewModel levelUp = (LevelUpViewModel) o;
        return getLevelUpId() == levelUp.getLevelUpId() &&
                getCustomerId() == levelUp.getCustomerId() &&
                getPoints() == levelUp.getPoints() &&
                Objects.equals(getMemberDate(), levelUp.getMemberDate());

    }
    @Override
    public int hashCode() {
        return Objects.hash(getLevelUpId(), getCustomerId(), getPoints(), getMemberDate());
    }
}
