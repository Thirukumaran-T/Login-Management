package com.example.LoginManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "external_events")
public class ExternalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    private String externalEventId;
    private String provider; // GOOGLE / OUTLOOK / APPLE
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private boolean synced = false;
}
