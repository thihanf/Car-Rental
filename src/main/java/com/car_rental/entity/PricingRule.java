package com.car_rental.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pricing_rules")
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pricingId;

    private BigDecimal weekendMultiplier;
    private BigDecimal holidayMultiplier;
    private BigDecimal longRentDiscount;

    @ManyToOne
    @JoinColumn(name = "created_by_admin_id")
    private Admin createdBy;

    public PricingRule() {}

    public Long getPricingId() {
        return pricingId;
    }

    public void setPricingId(Long pricingId) {
        this.pricingId = pricingId;
    }

    public BigDecimal getWeekendMultiplier() {
        return weekendMultiplier;
    }

    public void setWeekendMultiplier(BigDecimal weekendMultiplier) {
        this.weekendMultiplier = weekendMultiplier;
    }

    public BigDecimal getHolidayMultiplier() {
        return holidayMultiplier;
    }

    public void setHolidayMultiplier(BigDecimal holidayMultiplier) {
        this.holidayMultiplier = holidayMultiplier;
    }

    public BigDecimal getLongRentDiscount() {
        return longRentDiscount;
    }

    public void setLongRentDiscount(BigDecimal longRentDiscount) {
        this.longRentDiscount = longRentDiscount;
    }

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Admin createdBy) {
        this.createdBy = createdBy;
    }
}
