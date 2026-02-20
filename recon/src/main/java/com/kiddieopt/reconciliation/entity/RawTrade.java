package com.kiddieopt.reconciliation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "raw_trades_v1", schema = "raw_schema_v1")
public class RawTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String eventId;

    @Column(name = "trade_id")
    private String tradeId;

    @Column(name = "side")
    private String side;

    @Column(name = "source")
    private String source;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;
    
    @Column(name = "created_at")
    private LocalDateTime tradeTimestamp;

    @Column(name = "kafka_offset")
    private Long kafkaOffset;

    @Column(name = "processed_flag")
    private Boolean processedFlag = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getTradeTimestamp() {
        return tradeTimestamp;
    }

    public void setTradeTimestamp(LocalDateTime tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp;
    }

    public Long getKafkaOffset() {
        return kafkaOffset;
    }

    public void setKafkaOffset(Long kafkaOffset) {
        this.kafkaOffset = kafkaOffset;
    }

    public Boolean getProcessedFlag() {
        return processedFlag;
    }

    public void setProcessedFlag(Boolean processedFlag) {
        this.processedFlag = processedFlag;
    }

    

    

    

    
}
