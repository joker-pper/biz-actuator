package com.joker17.bizactuator.example;

import com.joker17.bizactuator.context.BizSimpleContext;
import com.joker17.bizactuator.core.BizActuator;
import com.joker17.bizactuator.resolver.AbstractBizResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BizActuatorExample {

    public static void main(String[] args) {
        BizExampleResolver bizExampleResolver = new BizExampleResolver();
        Integer bizParameter = 8;
        BizSimpleContext<List<String>> bizSimpleContext = new BizSimpleContext<>();
        String result = BizActuator.of(bizParameter, bizSimpleContext, bizExampleResolver).execute();
        System.out.println(String.format("【print-result】%s", result));
    }


    static class BizExampleResolver extends AbstractBizResolver<Integer, BizSimpleContext<List<String>>, String> {

        @Override
        public void check(Integer bizParameter, BizSimpleContext<List<String>> bizContext) {
            System.out.println("【BizExampleResolver.check】enter...");
            if (bizParameter <= 0) {
                throw new IllegalArgumentException("invalid bizParameter: " + bizParameter);
            }
        }

        @Override
        public String execute(Integer bizParameter, BizSimpleContext<List<String>> bizContext) {
            System.out.println("【BizExampleResolver.execute】enter...");
            List<String> dataList = new ArrayList<>(16);
            for (int i = 0; i < bizParameter; i++) {
                dataList.add("wow!");
            }
            bizContext.setDataObject(dataList);
            return bizContext.getDataObject().stream().collect(Collectors.joining("~"));
        }

        @Override
        public void executeAfter(Integer bizParameter, BizSimpleContext<List<String>> bizContext, String result) {
            System.out.println("【BizExampleResolver.executeAfter】enter...");
        }
    }
}
