package com.events.api.domain.event;

import com.events.api.domain.address.Address;
import com.events.api.domain.coupon.Coupon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "event")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private String imageUrl;
    private String eventUrl;
    private boolean remote;
    private Date date;

    @OneToOne(mappedBy = "event",cascade = CascadeType.ALL)
    private Address address;

}
