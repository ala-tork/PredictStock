package com.project.predictstock.Repositories;

import com.project.predictstock.Entities.Company;
import com.project.predictstock.Entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,String> {
}
