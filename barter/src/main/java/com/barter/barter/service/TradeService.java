package com.barter.barter.service;

import com.barter.barter.data.State;
import com.barter.barter.data.dto.trade.TradeInfoDTO;
import com.barter.barter.data.entity.ProductEntity;
import com.barter.barter.data.entity.TradeEntity;
import com.barter.barter.data.repository.ProductRepository;
import com.barter.barter.data.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeService {
    private final TradeRepository tradeRepository;
    private final ProductRepository productRepository;

    public TradeInfoDTO postTrade(Long pushId, Long acceptId){
        ProductEntity pushProduct = productRepository.findById(pushId).get();
        ProductEntity acceptProduct = productRepository.findById(acceptId).get();
        TradeEntity trade = TradeEntity.builder()
                .pushProduct(pushProduct)
                .acceptProduct(acceptProduct)
                .tradeState(State.SELLING)
                .build();
        tradeRepository.save(trade);
        TradeInfoDTO tradeInfoDTO = new TradeInfoDTO(trade.getId(), pushId, pushProduct.getUser().getUser_id(), acceptId, acceptProduct.getUser().getUser_id(), trade.getTradeState());
        return tradeInfoDTO;
    }

    public List<TradeInfoDTO> getAllTrade() {
        return tradeRepository.findAll().stream().map(TradeEntity::toDto).collect(Collectors.toList());
    }

    public List<TradeInfoDTO> searchPushTrade(String id){
        return tradeRepository.findByPushProduct_User_User_id(id).stream().map(TradeEntity::toDto).collect(Collectors.toList());
    }

    public List<TradeInfoDTO> searchAcceptTrade(String id){
        return tradeRepository.findByAcceptProduct_User_User_id(id).stream().map(TradeEntity::toDto).collect(Collectors.toList());
    }

    public TradeInfoDTO getTradeDetail(Long id){
        TradeEntity trade = tradeRepository.findById(id).get();
        return new TradeInfoDTO(trade.getId(), trade.getPushProduct().getId(), trade.getPushProduct().getUser().getUser_id(), trade.getAcceptProduct().getId(), trade.getAcceptProduct().getUser().getUser_id(), trade.getTradeState());
    }

    public void deleteTrade(Long id){
        tradeRepository.deleteById(id);
    }

    public TradeInfoDTO updateTradeState(Long id){
        TradeEntity trade = tradeRepository.findById(id).get();
        trade.setTradeState(State.SELLED);
        tradeRepository.save(trade);
        return new TradeInfoDTO(trade.getId(), trade.getPushProduct().getId(), trade.getPushProduct().getUser().getUser_id(), trade.getAcceptProduct().getId(), trade.getAcceptProduct().getUser().getUser_id(), trade.getTradeState());
    }

    public List<TradeInfoDTO> searchAllTrade(String id){
        TradeInfoDTO[] pushTradeList = tradeRepository.findByPushProduct_User_User_id(id).stream().map(TradeEntity::toDto).toList().toArray(new TradeInfoDTO[0]);
        TradeInfoDTO[] acceptTradeList = tradeRepository.findByAcceptProduct_User_User_id(id).stream().map(TradeEntity::toDto).toList().toArray(new TradeInfoDTO[0]);
        TradeInfoDTO[] tradeList = new TradeInfoDTO[pushTradeList.length + acceptTradeList.length];
        System.arraycopy(pushTradeList, 0, tradeList, 0, pushTradeList.length);
        System.arraycopy(acceptTradeList, 0, tradeList, pushTradeList.length, acceptTradeList.length);
        Arrays.sort(tradeList);
        return Arrays.stream(tradeList).toList();
    }

    public List<TradeInfoDTO> searchTradeState(String id, String traded){
        State state = State.SELLING;
        if(traded.equals("1")){
            state = State.SELLED;
        }else if(!(traded.equals("0"))) return null;
        TradeInfoDTO[] pushTradeList = (TradeInfoDTO[]) tradeRepository.findByTradeStateAndPushProduct_User_User_id(state, id).stream().map(TradeEntity::toDto).toArray();
        TradeInfoDTO[] acceptTradeList = (TradeInfoDTO[]) tradeRepository.findByTradeStateAndAcceptProduct_User_User_id(state, id).stream().map(TradeEntity::toDto).toArray();
        TradeInfoDTO[] tradeList = new TradeInfoDTO[pushTradeList.length + acceptTradeList.length];
        System.arraycopy(pushTradeList, 0, tradeList, 0, pushTradeList.length);
        System.arraycopy(acceptTradeList, 0, tradeList, pushTradeList.length, acceptTradeList.length);
        Arrays.sort(tradeList);
        return Arrays.stream(tradeList).toList();
    }
}
