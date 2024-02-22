package com.rewards.api.Store.StoreHoliday;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "storeholiday")
public class StoreHolidayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "storeId", nullable = false)
    private Long storeId;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    public Long getId() {
        return id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}

