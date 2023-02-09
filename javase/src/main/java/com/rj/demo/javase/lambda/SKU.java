package com.rj.demo.javase.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SKU {
    Long id;
    String name;
    Long categoryId;
    String categoryName;
    Double price;
    int sales;
    Long shopId;
    String shopName;
    int stock;

    public List<SKU> filterSKU_V1(List<SKU> sourceSKUList) {
        List<SKU> result = new ArrayList<>();
        for (SKU sku : sourceSKUList) {
           if("笔记本".equals(sku.getCategoryName())) {
               result.add(sku);
           }
        }
        return result;
    }

    //数据参数化
    public List<SKU> filterSKU_V2(List<SKU> sourceSKUList, String categoryName) {
        List<SKU> result = new ArrayList<>();
        for (SKU sku : sourceSKUList) {
           if(categoryName.equals(sku.getCategoryName())) {
               result.add(sku);
           }
        }
        return result;
    }

    List<SKU> filterSKU_V3(List<SKU> sourceSKUList, String categoryName, Double price) {
        List<SKU> result = new ArrayList<>();
        for (SKU sku : sourceSKUList) {
            if(categoryName.equals(sku.getCategoryName()) && sku.getPrice() > price) {
                result.add(sku);
            }
        }
        return result;
    }

    List<SKU> filterSKU_V4(List<SKU> sourceSKUList, String categoryName, Double price) {
        List<SKU> result = new ArrayList<>();
        for (SKU sku : sourceSKUList) {
            if(categoryName.equals(sku.getCategoryName()) && sku.getPrice() > price) {
                result.add(sku);
            }
        }
        return result;
    }



}

interface SKUFilterStrategy {
    boolean filter(SKU sku);
}

class CategoryNameFilterStrategy implements SKUFilterStrategy {

    @Override
    public boolean filter(SKU sku) {
        return sku.getCategoryName().equals("笔记本i");
    }
}

class PriceFilterStrategy implements SKUFilterStrategy {

    @Override
    public boolean filter(SKU sku) {
        return sku.getPrice() > 1000;
    }
}

class  SKUService {
    List<SKU> filterSKUByStrategy(List<SKU> sourceSKUList,SKUFilterStrategy strategy) {
        List<SKU> result = new ArrayList<>();
        for (SKU sku : sourceSKUList) {
            if(strategy.filter(sku)) {
                result.add(sku);
            }
        }
        return result;
    }
    List<SKU> filterSKUByStrategyV2(List<SKU> sourceSKUList) {
        List<SKU> result = new ArrayList<>();
        filterSKUByStrategy(sourceSKUList, new SKUFilterStrategy() {
            @Override
            public boolean filter(SKU sku) {
                return sku.getPrice() > 10000;
            }
        });
        return result;
    }

    List<SKU> filterSKUByStrategyV3(List<SKU> sourceSKUList) {
        List<SKU> result = new ArrayList<>();
        filterSKUByStrategy(sourceSKUList, sku -> sku.getPrice() > 10000);
        return result;
    }
}
