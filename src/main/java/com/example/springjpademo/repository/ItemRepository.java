package com.example.springjpademo.repository;

import com.example.springjpademo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    //SELECT WHERE Equal cond
    List<Item> findByItemNm(String itemNm);
    //SELECT WHERE OR cond
    List<Item> findByItemNmOrItemDetail(String ItemNm, String ItemDetail);
    //SELECT WHERE <(LessThan) cond
    List<Item> findByPriceLessThan(Integer price);
    //SELECT WHERE <(LessThan) cond & OrderBy desc
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
    //JPQL
    @Query("SELECT i " +
           "FROM Item i " +
           "WHERE i.itemDetail like %:itemDetail% " +
           "ORDER BY i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
    //Native Query
    @Query(value="SELECT * " +
            "FROM item i " +
            "WHERE i.item_detail like %:itemDetail% " +
            "ORDER BY i.price desc"
          , nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
}
