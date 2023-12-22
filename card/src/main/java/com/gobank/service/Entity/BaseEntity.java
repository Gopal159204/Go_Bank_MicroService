package com.gobank.service.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(updatable = false )
    @LastModifiedDate
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(updatable = false)
    private  String createBy;
    @LastModifiedDate
    @Column(insertable = false)

    private LocalDateTime  updatedAt;
    @LastModifiedBy
    @Column(insertable = false)

    private  String updatedBy;

}
