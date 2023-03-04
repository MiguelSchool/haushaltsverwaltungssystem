package com.example.haushaltsverwaltungssystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "objectId", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long objectId;

    @Column(name = "createdDate", updatable = false/*, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP"*/)
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(name = "updatedDate"/*, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP"*/)
    @UpdateTimestamp
    private LocalDate updatedDate;

    @Column(name = "active", columnDefinition = "boolean default false")
    private boolean active;
}
