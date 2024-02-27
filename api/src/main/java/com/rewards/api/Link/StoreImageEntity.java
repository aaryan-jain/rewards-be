package com.rewards.api.Link;

import com.rewards.api.Shared.ImageType;
import com.rewards.api.Store.StoreEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "storeimagelink")
public class StoreImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name="linktoimage", nullable = false)
    private String linktoimage;

    @Enumerated(EnumType.STRING)
    @Column(name="imagetype")
    private ImageType imagetype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storeId")
    private StoreEntity store;

    public StoreImageEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getLinktoimage() {
        return linktoimage;
    }

    public ImageType getImagetype() {
        return imagetype;
    }

}
