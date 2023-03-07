package com.barter.barter.data.repository;

import com.barter.barter.data.State;
import com.barter.barter.data.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<TradeEntity, Long> {
    List<TradeEntity> findByTradeStateAndAcceptProduct_User_User_id(State tradeState, String user_id);
    List<TradeEntity> findByTradeStateAndPushProduct_User_User_id(State tradeState, String user_id);
    List<TradeEntity> findByAcceptProduct_User_User_id(String user_id);
    List<TradeEntity> findByPushProduct_User_User_id(String user_id);

}
