//package com.yjh.lease.web.admin.custom.converter;
//
//import enums.com.atguigu.lease.model.ItemType;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StringToItemTypeConverter implements Converter<String, ItemType> {
//
//    @Override
//    public ItemType convert(String code) {
//        ItemType[] values = ItemType.values();
//        for (ItemType itemType : values) {
//            if (itemType.getCode().equals(Integer.valueOf(code))) {
//                return itemType;
//            }
//        }
//        throw new IllegalArgumentException("code"+code+"非法");
//    }
//}
