package com.joker17.bizactuator.example;

import com.joker17.bizactuator.context.BizSimpleContext;
import com.joker17.bizactuator.core.BizRichActuator;
import com.joker17.bizactuator.resolver.AbstractBizResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BizRichActuatorExample {

    public static void main(String[] args) {

        BizRichExampleResolver bizChainExampleResolver = new BizRichExampleResolver();

        BizRichExampleParameter bizChainExampleParameter = new BizRichExampleParameter();
        bizChainExampleParameter.setData(1);

        {
            BizSimpleContext<List<String>> bizSimpleContext = new BizSimpleContext<>();

            String result = BizRichActuator.of(bizChainExampleParameter, bizSimpleContext, bizChainExampleResolver)
                    .appendConsumer(bizChainExampleResolver::reduce)
                    .appendConsumer(bizChainExampleResolver::reduce)
                    .execute();
            System.out.println(String.format("【print-result】%s", result));
        }

        System.out.println("--------------------分割线--------------------");

        {
            BizSimpleContext<List<String>> bizSimpleContext = new BizSimpleContext<>();
            BizRichActuator<BizRichExampleParameter, BizSimpleContext<List<String>>, String> bizRichActuator = BizRichActuator.of(bizChainExampleParameter, bizSimpleContext, bizChainExampleResolver)
                    .appendConsumer(bizChainExampleResolver::reduce)
                    .appendConsumer(bizChainExampleResolver::reduce)
                    .appendConsumer(bizChainExampleResolver::reduce);
            System.out.println(String.format("【print-result】%s", bizRichActuator.execute()));
        }

    }

    static class BizRichExampleParameter {

        private Integer data;

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }
    }

    static class BizRichExampleResolver extends AbstractBizResolver<BizRichExampleParameter, BizSimpleContext<List<String>>, String> {

        @Override
        public void check(BizRichExampleParameter bizParameter, BizSimpleContext<List<String>> bizContext) {
            System.out.println("【BizRichExampleResolver.check】enter...");
            Integer data = bizParameter.getData();
            Objects.requireNonNull(data, "data must be not null!");
            if (data <= 0) {
                throw new IllegalArgumentException("invalid data: " + data);
            }
        }

        public void reduce(BizRichExampleParameter bizParameter, BizSimpleContext<List<String>> bizContext) {
            System.out.println("【BizRichExampleResolver.reduce】enter...");
            List<String> list = bizContext.getDataObject();
            if (list == null) {
                list = new ArrayList<>(16);
                bizContext.setDataObject(list);
            }
            list.add("wow!");
        }

        @Override
        public String execute(BizRichExampleParameter bizParameter, BizSimpleContext<List<String>> bizContext) {
            System.out.println("【BizRichExampleResolver.execute】enter...");
            return bizContext.getDataObject().stream().collect(Collectors.joining("\t"));
        }

        @Override
        public void executeAfter(BizRichExampleParameter bizParameter, BizSimpleContext<List<String>> bizContext, String result) {
            System.out.println("【BizRichExampleResolver.executeAfter】enter...");
        }
    }
}
