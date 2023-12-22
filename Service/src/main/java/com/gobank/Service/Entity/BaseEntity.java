package com.gobank.Service.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
     @CreatedBy
     @Column(updatable = false)

     private String createdBy;
     @LastModifiedDate
     @Column(insertable = false)
    private LocalDateTime updatedAt;
     @LastModifiedBy
     @Column(insertable = false)

     private String updatedBY;
}
