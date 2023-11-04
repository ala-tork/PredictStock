package com.project.predictstock.Repositories;

import com.project.predictstock.Entities.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType,String> {
}
