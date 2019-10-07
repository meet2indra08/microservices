package com.forex;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExcahangerValue, Long> {
	
	ExcahangerValue findByFromAndTo(String from, String to);

}
