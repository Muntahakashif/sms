package com.sms.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

    @Entity
    @Data
    public class Fee {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        private String month;
        private String givenBy;
        private Long amount;
        private String description;
        private Date createdDate;

        @ManyToOne(fetch = FetchType.LAZY,optional=false)
        @JoinColumn(name="user_id",nullable=false)
        @JsonIgnore
        @OnDelete(action= OnDeleteAction.CASCADE)
        private User user;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getGivenBy() {
            return givenBy;
        }

        public void setGivenBy(String givenBy) {
            this.givenBy = givenBy;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
