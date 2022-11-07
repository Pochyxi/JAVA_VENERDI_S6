package com.example.java_venerdi_s5.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "devices")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String name;


    @Enumerated(EnumType.STRING)
    DeviceStatus deviceStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
