package com.barter.barter.controller;

import com.barter.barter.data.dto.ErrorDTO;
import com.barter.barter.data.dto.trade.SuggestTradeDTO;
import com.barter.barter.data.dto.trade.TradeInfoDTO;
import com.barter.barter.data.entity.TradeEntity;
import com.barter.barter.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trade")
public class TradeController {
    private final TradeService tradeService;

    @GetMapping
    public ResponseEntity<List<TradeInfoDTO>> getAllTrade(){
        try{
            List<TradeInfoDTO> tradeList = tradeService.getAllTrade();
            return new ResponseEntity<List<TradeInfoDTO>>(tradeList, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/search?push={id}")
    public ResponseEntity<List<TradeInfoDTO>> searchPushTrade(@RequestParam(required = false) String push,
                                                              @RequestParam(required = false) String accept,
                                                              @RequestParam(required = false) String user,
                                                              @RequestParam(required = false) String traded){
        if(push != null && accept == null && traded == null && user == null) {
            try {
                List<TradeInfoDTO> tradeList = tradeService.searchPushTrade(push);
                return new ResponseEntity<List<TradeInfoDTO>>(tradeList, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }else if(push == null && accept != null && traded == null && user == null){
            try{
                List<TradeInfoDTO> tradeList = tradeService.searchAcceptTrade(accept);
                return new ResponseEntity<List<TradeInfoDTO>>(tradeList, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }else if(push == null && accept == null && traded != null && user != null){
            try {
                List<TradeInfoDTO> tradeList = tradeService.searchTradeState(traded, user);
                if(tradeList==null) return ResponseEntity.badRequest().build();
                return new ResponseEntity<List<TradeInfoDTO>>(tradeList, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }else if(push == null && accept == null && traded == null && user != null){
            try {
                List<TradeInfoDTO> tradeList = tradeService.searchAllTrade(user);
                return new ResponseEntity<List<TradeInfoDTO>>(tradeList, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TradeInfoDTO> getTradeDetail(@PathVariable Long id){
        try {
            TradeInfoDTO tradeInfoDTO = tradeService.getTradeDetail(id);
            return new ResponseEntity<TradeInfoDTO>(tradeInfoDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity postTrade(@RequestBody SuggestTradeDTO suggestTradeDTO){
        try{
            TradeInfoDTO tradeInfoDTO = tradeService.postTrade(suggestTradeDTO.getPushId(), suggestTradeDTO.getAcceptId());
            return new ResponseEntity<TradeInfoDTO>(tradeInfoDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ErrorDTO>(new ErrorDTO("CREATE FAILED", "트레이드 생성 중 오류가 생겼습니다."), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTrade(@PathVariable Long id){
        try {
            tradeService.deleteTrade(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TradeInfoDTO> updateTrade(@PathVariable Long id){
        try {
            TradeInfoDTO tradeInfoDTO = tradeService.updateTradeState(id);
            return new ResponseEntity<TradeInfoDTO>(tradeInfoDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
